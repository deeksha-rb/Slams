package com.sayatech.slambook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Slams extends AppCompatActivity implements KnowMe.GetToKnowData,
                             TalkFaves.TalkFavesData , BrowsingMore.BrowsingMoreData {


    TabLayout tabLayout;
    ViewPager viewPager;
    SlamsModel slamsModel;
    SlamsDataBase slamsDataBase;
    boolean getToKnowDataCollected = false,
            talkFavesDataCollected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_slams);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        MaterialToolbar topAppBar = findViewById(R.id.topAppBar);

        topAppBar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        ViewPagerSlamAdapter viewPagerSlamAdapter = new ViewPagerSlamAdapter(
                                                        getSupportFragmentManager(),
                                        FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerSlamAdapter);
        tabLayout.setupWithViewPager(viewPager);
        slamsModel = new SlamsModel(-1, false,"","","","","",
                "","","","","","",
                "","","","","","",
                "","","","","","","",
                "","","","");

        slamsDataBase = new SlamsDataBase(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (viewPagerSlamAdapter.getCurrentFragment() instanceof KnowMe) {
                    KnowMe getToKnow = (KnowMe) viewPagerSlamAdapter.getCurrentFragment();
                    getToKnowDataCollected = getToKnow.passingData();

                } else if (viewPagerSlamAdapter.getCurrentFragment() instanceof TalkFaves) {
                    TalkFaves talkFaves = (TalkFaves) viewPagerSlamAdapter.getCurrentFragment();
                    talkFavesDataCollected = talkFaves.passingData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onGetToKnowData(SlamsModel model) {

        slamsModel.goodName = model.goodName;
        slamsModel.knownAs = model.knownAs;
        slamsModel.bornOn = model.bornOn;
        slamsModel.zodiacSign = model.zodiacSign;
        slamsModel.mailID = model.mailID;
        slamsModel.phoneNumber= model.phoneNumber;
        slamsModel.relationshipStatus = model.relationshipStatus;
        slamsModel.words = model.words;
    }

    @Override
    public void onTalkFavesData(SlamsModel model){

        slamsModel.favColor = model.favColor;
        slamsModel.favFood = model.favFood;
        slamsModel.favPlace = model.favPlace;
        slamsModel.favSport = model.favSport;
        slamsModel.favMovieStar = model.favMovieStar;
        slamsModel.favCartoonChar = model.favCartoonChar;
        slamsModel.favBandOrSinger = model.favBandOrSinger;
        slamsModel.favMovie = model.favMovie;
        slamsModel.roleModel = model.roleModel;
        slamsModel.liveQuote = model.liveQuote;
        talkFavesDataCollected = true;
    }

    @Override
    public void onBrowsingMoreData(SlamsModel model) {
        slamsModel.hobby = model.hobby;
        slamsModel.crazyAbout = model.crazyAbout;
        slamsModel.fascinatedBy = model.fascinatedBy;
        slamsModel.ambition = model.ambition;
        slamsModel.genie = model.genie;
        slamsModel.rulePresident = model.rulePresident;
        slamsModel.badAt = model.badAt;
        slamsModel.describeLove = model.describeLove;
        slamsModel.describeFriendship = model.describeFriendship;
        slamsModel.aboutYou = model.aboutYou;


        if(getToKnowDataCollected & talkFavesDataCollected){
            if (getIntent().hasExtra("ID")) {
                String ID = getIntent().getStringExtra("ID");
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> slam = new HashMap<>();
                slam.put("id", -1);
                slam.put("goodName", slamsModel.goodName);
                slam.put("knownAs", slamsModel.knownAs );
                slam.put("bornOn", slamsModel.bornOn );
                slam.put("zodiacSign", slamsModel.zodiacSign);
                slam.put("mailID", slamsModel.mailID);
                slam.put("phoneNumber", slamsModel.phoneNumber);
                slam.put("relationshipStatus", slamsModel.relationshipStatus);
                slam.put("words", slamsModel.words);
                slam.put("favColor", slamsModel.favColor);
                slam.put("favFood", slamsModel.favFood);
                slam.put("favPlace", slamsModel.favPlace);
                slam.put("favSport", slamsModel.favSport);
                slam.put("favMovieStar", slamsModel.favMovieStar);
                slam.put("favCartoonChar", slamsModel.favCartoonChar);
                slam.put("favBandOrSinger", slamsModel.favBandOrSinger);
                slam.put("favMovie", slamsModel.favMovie);
                slam.put("roleModel", slamsModel.roleModel);
                slam.put("liveQuote", slamsModel.liveQuote);
                slam.put("hobby", slamsModel.hobby);
                slam.put("crazyAbout", slamsModel.crazyAbout);
                slam.put("fascinatedBy", slamsModel.fascinatedBy);
                slam.put("ambition", slamsModel.ambition);
                slam.put("genie", slamsModel.genie);
                slam.put("rulePresident", slamsModel.rulePresident);
                slam.put("badAt", slamsModel.badAt);
                slam.put("describeLove", slamsModel.describeLove);
                slam.put("describeFriendship", slamsModel.describeFriendship);
                slam.put("aboutYou", slamsModel.aboutYou);
                slam.put("filled", true);

                db.collection("slams").document(ID)
                        .set(slam)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Slams.this, "Successfully Submitted!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Slams.this, "Error while submitting-_-",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                slamsDataBase.addSlams(slamsModel);
            }
            getOnBackPressedDispatcher().onBackPressed();

        }else if (!getToKnowDataCollected){
            viewPager.setCurrentItem(0);
            Toast.makeText(getApplicationContext(), "Please fill all the details" ,
                    Toast.LENGTH_SHORT).show();
        }else{
            viewPager.setCurrentItem(1);
            Toast.makeText(getApplicationContext(), "Please fill all the details" ,
                    Toast.LENGTH_SHORT).show();
        }


    }
}