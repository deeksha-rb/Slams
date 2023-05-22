package com.sayatech.slambook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.dynamiclinks.ShortDynamicLink;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

import androidx.core.splashscreen.SplashScreen;

public class SlamActivity extends AppCompatActivity  implements SlamRecyclerViewInterface {

    ArrayList<SlamsModel> arrSlams = new ArrayList<>();
    private SlamsDataBase slamsDataBase;
    private SlamsAdapter adapter;
    SlamsModel deletedSlam;
    ImageView welcomeAnim;
    TextView welcomeText;
    boolean appReady = false;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        sharedPreferences = getSharedPreferences("slamsSharedPrefs", Context.MODE_PRIVATE);

        View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if(appReady) {
                    String ID= sharedPreferences.getString("ID", "");
                    Log.d("TAG", "onPreDraw: " + ID);
                    if (!ID.isEmpty()) {
                        checkIfDocFilled(ID);
                    }
                    content.getViewTreeObserver().removeOnPreDrawListener(this);
                }
                displaySplashScreen();
                return false;
            }

        });

        setContentView(R.layout.activity_main);
        Toolbar materialToolbar = (Toolbar) findViewById(R.id.topAppBar);
        setSupportActionBar(materialToolbar);

        ExtendedFloatingActionButton create = (ExtendedFloatingActionButton) findViewById(R.id.extendedFabCreate);
        RecyclerView recyclerView = findViewById(R.id.recyclerSlam);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        slamsDataBase = new SlamsDataBase(this);
        arrSlams = slamsDataBase.fetchSlams();
        adapter= new SlamsAdapter(this,arrSlams, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        welcomeAnim = findViewById(R.id.welcome_anim);
        welcomeText = findViewById(R.id.welcome_text);

        getDynamicLinksFromFirebase();

        Dialog slamChoiceDialog = new Dialog(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slamChoiceDialog.setContentView(R.layout.slam_choice_dialog);
                slamChoiceDialog.getWindow()
                        .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                slamChoiceDialog.getWindow().getAttributes().windowAnimations =
                        com.google.android.material.R.style.Animation_AppCompat_Dialog;
                slamChoiceDialog.show();

                Button manually = slamChoiceDialog.findViewById(R.id.manually);
                Button digitally = slamChoiceDialog.findViewById(R.id.link_share);
                EditText help = slamChoiceDialog.findViewById(R.id.need_help);

                manually.setOnClickListener(v1 -> {
                    startActivity(new Intent(getApplicationContext(), Slams.class));
                    slamChoiceDialog.dismiss();
                });
                digitally.setOnClickListener(v12 -> {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    Map<String, Object> slam = new HashMap<>();
                    slam.put("filled", false);

                    db.collection("slams")
                            .add(slam)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Task<ShortDynamicLink> dynamicLink = FirebaseDynamicLinks
                                            .getInstance()
                                            .createDynamicLink()
                                            .setLink(Uri.parse("https://www.slams.com/?id="
                                                    + documentReference.getId()))
                                            .setDomainUriPrefix("https://slams.page.link")
                                            .setAndroidParameters(
                                                    new DynamicLink
                                                            .AndroidParameters
                                                            .Builder("com.sayatech.slambook")
                                                            .build()
                                            ).buildShortDynamicLink(ShortDynamicLink.Suffix.SHORT)
                                            .addOnCompleteListener(new OnCompleteListener<ShortDynamicLink>() {
                                                @Override
                                                public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                                                    Uri shortLink = task.getResult().getShortLink();
                                                    Uri flowchartLink = task.getResult().getPreviewLink();
                                                    Log.d("TAG", shortLink.toString());

                                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                                    editor.putString("ID", documentReference.getId());
                                                    editor.apply();

                                                    Intent shareIntent = new Intent();
                                                    String shareMessage = "Fill it out for me! \n" + shortLink;
                                                    shareIntent.setAction(Intent.ACTION_SEND);
                                                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                                                    shareIntent.setType("text/plain");
                                                    startActivity(shareIntent);
                                                    slamChoiceDialog.dismiss();
                                                }
                                            });
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", e.toString());
                                    Toast.makeText(
                                            SlamActivity.this,
                                            "Error while generating a link",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                });
                help.setOnClickListener(v13 -> {
                    startActivity(new Intent(SlamActivity.this, help.class));
                    slamChoiceDialog.dismiss();

                });
            }
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        if(adapter.getItemCount() == 0) {
            welcomeAnim.setVisibility(View.VISIBLE);
            welcomeText.setVisibility(View.VISIBLE);
        } else {
            welcomeText.setVisibility(View.GONE);
            welcomeAnim.setVisibility(View.GONE);
        }

    }

    private void checkIfDocFilled(String id) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Log.d("TAG", "checkIfDocFilled:" + id);
        db.collection("slams").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (Boolean.TRUE.equals(documentSnapshot.getBoolean("filled"))) {
                            SlamsModel slamsModel = documentSnapshot.toObject(SlamsModel.class);
                            if (slamsModel != null)  {
                                slamsDataBase.addSlams(slamsModel);
                                arrSlams.add(slamsModel);
                                adapter.notifyItemInserted(arrSlams.indexOf(slamsModel));
                                db.collection("slams").document(id)
                                        .delete();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("ID", "");
                                editor.apply();
                            }
                        }
                    }
                });
    }


    private void getDynamicLinksFromFirebase() {
        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        Uri deeplink = null;
                        if (pendingDynamicLinkData != null) {
                            deeplink = pendingDynamicLinkData.getLink();
                        }
                        if (deeplink != null) {
                            String id = deeplink.toString().substring(26);
                            Log.d("TAG", deeplink.toString());
                            Log.d("TAG", "onSuccess: " + id);
                            Intent receiveIntent = new Intent(getApplicationContext(), Slams.class);
                            receiveIntent.putExtra("ID", id);
                            startActivity(receiveIntent);
                        }
                    }
                });
    }

    private void displaySplashScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                appReady = true;
            }
        }, 500);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback
            (0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT)
    {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target)
        {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            int position = viewHolder.getAbsoluteAdapterPosition();
            deletedSlam = arrSlams.get(position);
            int id = arrSlams.get(position).id;
            arrSlams.remove(position);
            adapter.notifyItemRemoved(position);

            final AlertDialog.Builder builder = new AlertDialog.Builder(SlamActivity.this)
                    .setTitle("Delete Slam")
                    .setMessage("Are you sure you wanna delete this slam?")
                    .setIcon(R.drawable.ic_baseline_delete_24)
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        int r = slamsDataBase.deleteSlam(id);
                        if(adapter.getItemCount() == 0) {
                            welcomeAnim.setVisibility(View.VISIBLE);
                            welcomeText.setVisibility(View.VISIBLE);
                        } else {
                            welcomeText.setVisibility(View.GONE);
                            welcomeAnim.setVisibility(View.GONE);
                        }
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> {
                        arrSlams.add(position,deletedSlam);
                        adapter.notifyItemInserted(position);
                    });
            builder.show();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(SlamActivity.this ,R.color.delete_color))
                    .addActionIcon(R.drawable.ic_baseline_delete_24)
                    .setSwipeLeftLabelColor(ContextCompat.getColor(SlamActivity.this ,R.color.white))
                    .setSwipeRightLabelColor(ContextCompat.getColor(SlamActivity.this ,R.color.white))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    private void slamList(String text) {
        ArrayList<SlamsModel> filteredList = new ArrayList<>();
        for (SlamsModel slamsModel: arrSlams){
            if(slamsModel.getGoodName().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))
                    | slamsModel.getKnownAs().toLowerCase(Locale.ROOT).contains(text.toLowerCase(Locale.ROOT))) {
                        filteredList.add(slamsModel);
            }
        }
        adapter.setFilteredList(filteredList);
        if (filteredList.isEmpty()){
            Toast.makeText(this,"*No data found*" ,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrSlams = slamsDataBase.fetchSlams();
        adapter.setItems(arrSlams);
        adapter.notifyDataSetChanged();

        if(adapter.getItemCount() == 0) {
            welcomeAnim.setVisibility(View.VISIBLE);
            welcomeText.setVisibility(View.VISIBLE);
        } else {
            welcomeText.setVisibility(View.GONE);
            welcomeAnim.setVisibility(View.GONE);
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(SlamActivity.this , SlamDisplay.class);

        intent.putExtra("GOOD_NAME" , arrSlams.get(position).goodName);
        intent.putExtra("KNOWN_AS" , arrSlams.get(position).knownAs);
        intent.putExtra("BORN_ON" , arrSlams.get(position).bornOn);
        intent.putExtra("ZODIAC_SIGN" , arrSlams.get(position).zodiacSign);
        intent.putExtra("EMAIL_ID" , arrSlams.get(position).mailID);
        intent.putExtra("PHONE_NUMBER" , arrSlams.get(position).phoneNumber);
        intent.putExtra("RELATIONSHIP_STATUS" , arrSlams.get(position).relationshipStatus);
        intent.putExtra("WORDS" , arrSlams.get(position).words);
        intent.putExtra("COLOR" , arrSlams.get(position).favColor);
        intent.putExtra("FOOD" , arrSlams.get(position).favFood);
        intent.putExtra("PLACE" , arrSlams.get(position).favPlace);
        intent.putExtra("SPORT" , arrSlams.get(position).favSport);
        intent.putExtra("MOVIE_STAR" , arrSlams.get(position).favMovieStar);
        intent.putExtra("CARTOON_CHAR" , arrSlams.get(position).favCartoonChar);
        intent.putExtra("BAND_SINGER" , arrSlams.get(position).favBandOrSinger);
        intent.putExtra("FAV_MOVIE" , arrSlams.get(position).favMovie);
        intent.putExtra("ROLE_MODEL" , arrSlams.get(position).roleModel);
        intent.putExtra("LIVE_QUOTE" , arrSlams.get(position).liveQuote);
        intent.putExtra("HOBBIES" , arrSlams.get(position).hobby);
        intent.putExtra("CRAZY_ABOUT" , arrSlams.get(position).crazyAbout);
        intent.putExtra("FASCINATED_BY" , arrSlams.get(position).fascinatedBy);
        intent.putExtra("AMBITION" , arrSlams.get(position).ambition);
        intent.putExtra("GENIE" , arrSlams.get(position).genie);
        intent.putExtra("PRESIDENT_RULE" , arrSlams.get(position).rulePresident);
        intent.putExtra("BAD_AT" , arrSlams.get(position).badAt);
        intent.putExtra("ABOUT_LOVE" , arrSlams.get(position).describeLove);
        intent.putExtra("ABOUT_FRIENDSHIP" , arrSlams.get(position).describeFriendship);
        intent.putExtra("ABOUT_YOU" , arrSlams.get(position).aboutYou);

        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        getMenuInflater().inflate(R.menu.menu_items,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_menu) {
            SearchView searchView = (SearchView) item.getActionView();
            searchView.setQueryHint("Search for Slams!!");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {return false;}

                @Override
                public boolean onQueryTextChange(String newText) {
                    slamList(newText);
                    return true;
                }
            });
        } else if (id == R.id.invite) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String inviteMessage = "https://play.google.com/store/apps/details?id="
                    + BuildConfig.APPLICATION_ID ;
            intent.putExtra(Intent.EXTRA_TEXT, "Let's start filling our slams! " +
                    "It's digital form of slamBook \nGet it at --> \n" + inviteMessage);
            startActivity(Intent.createChooser(intent, "Share via"));
        } else if (id == R.id.aboutApp) {
            startActivity(new Intent(SlamActivity.this, AboutThisApp.class));
        } else if (id == R.id.help) {
            startActivity(new Intent(SlamActivity.this, help.class));
        } else if (id == R.id.contact_us) {
            startActivity(new Intent(SlamActivity.this, contact_us.class));
        }
        return true;
    }
}
