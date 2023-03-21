package com.sayatech.slambook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

public class SlamDisplay extends AppCompatActivity {

    TextInputEditText textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,
            textView9,textView10,textView11,textView12,textView13,textView14,textView15,textView16,
            textView17,textView18,textView19,textView20,textView21,textView22,textView23,
            textView24,textView25,textView26,textView27,textView28;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slam_display);

        String goodName = getIntent().getStringExtra("GOOD_NAME");
        String knownAS = getIntent().getStringExtra("KNOWN_AS");
        String bornOn = getIntent().getStringExtra("BORN_ON");
        String zodiacSign = getIntent().getStringExtra("ZODIAC_SIGN");
        String mailID = getIntent().getStringExtra("EMAIL_ID");
        String phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");
        String relationshipStatus = getIntent().getStringExtra("RELATIONSHIP_STATUS");
        String words = getIntent().getStringExtra("WORDS");
        String favColor = getIntent().getStringExtra("COLOR");
        String favFood = getIntent().getStringExtra("FOOD");
        String favPlace = getIntent().getStringExtra("PLACE");
        String favSport = getIntent().getStringExtra("SPORT");
        String favMovieStar = getIntent().getStringExtra("MOVIE_STAR");
        String favCartoonChar = getIntent().getStringExtra("CARTOON_CHAR");
        String bandOrSinger = getIntent().getStringExtra("BAND_SINGER");
        String favMovie = getIntent().getStringExtra("FAV_MOVIE");
        String roleModel = getIntent().getStringExtra("ROLE_MODEL");
        String liveQuote = getIntent().getStringExtra("LIVE_QUOTE");
        String hobbies = getIntent().getStringExtra("HOBBIES");
        String crazyAbout = getIntent().getStringExtra("CRAZY_ABOUT");
        String fascinatedBy = getIntent().getStringExtra("FASCINATED_BY");
        String ambition = getIntent().getStringExtra("AMBITION");
        String genie = getIntent().getStringExtra("GENIE");
        String rulePresident = getIntent().getStringExtra("PRESIDENT_RULE");
        String badAt = getIntent().getStringExtra("BAD_AT");
        String describeLove = getIntent().getStringExtra("ABOUT_LOVE");
        String describeFriendship = getIntent().getStringExtra("ABOUT_FRIENDSHIP");
        String aboutYou = getIntent().getStringExtra("ABOUT_YOU");

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView11 = findViewById(R.id.textView11);
        textView12 = findViewById(R.id.textView12);
        textView13 = findViewById(R.id.textView13);
        textView14 = findViewById(R.id.textView14);
        textView15 = findViewById(R.id.textView15);
        textView16 = findViewById(R.id.textView16);
        textView17 = findViewById(R.id.textView17);
        textView18 = findViewById(R.id.textView18);
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);
        textView21 = findViewById(R.id.textView21);
        textView22 = findViewById(R.id.textView22);
        textView23 = findViewById(R.id.textView23);
        textView24 = findViewById(R.id.textView24);
        textView25 = findViewById(R.id.textView25);
        textView26 = findViewById(R.id.textView26);
        textView27 = findViewById(R.id.textView27);
        textView28 = findViewById(R.id.textView28);

        textView1.setText(goodName);
        textView2.setText(knownAS);
        textView3.setText(bornOn);
        textView4.setText(zodiacSign);
        textView5.setText(mailID);
        textView6.setText(phoneNumber);
        textView7.setText(relationshipStatus);
        textView8.setText(words);
        textView9.setText(favColor);
        textView10.setText(favFood);
        textView11.setText(favPlace);
        textView12.setText(favSport);
        textView13.setText(favMovieStar);
        textView14.setText(favCartoonChar);
        textView15.setText(bandOrSinger);
        textView16.setText(favMovie);
        textView17.setText(roleModel);
        textView18.setText(liveQuote);
        textView19.setText(hobbies);
        textView20.setText(crazyAbout);
        textView21.setText(fascinatedBy);
        textView22.setText(ambition);
        textView23.setText(genie);
        textView24.setText(rulePresident);
        textView25.setText(badAt);
        textView26.setText(describeLove);
        textView27.setText(describeFriendship);
        textView28.setText(aboutYou);
    }
}