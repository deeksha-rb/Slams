package com.sayatech.slambook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SlamsDataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SlamsDB";
    public static final String TABLE_NAME = "SlamsTable";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_ROW_ID = "ID";
    public static final String KEY_NAME = "name";
    public static final String KEY_NICKNAME = "nickname";
    public static final String KEY_BORN_ON = "bornOn";
    public static final String KEY_ZODIAC = "zodiacSign";
    public static final String KEY_EMAIL = "mailId";
    public static final String KEY_PHONE_NUMBER = "phoneNumber";
    public static final String KEY_RELATIONSHIP = "relationshipStatus";
    public static final String KEY_WORDS = "words";
    public static final String KEY_COLOR = "favColor";
    public static final String KEY_FOOD = "favFood";
    public static final String KEY_PLACE = "favPlace";
    public static final String KEY_SPORT = "favSport";
    public static final String KEY_MOVIE_STAR = "movieStar";
    public static final String KEY_CARTOON_CHAR = "cartoonChar";
    public static final String KEY_SINGER = "bandOrSinger";
    public static final String KEY_MOVIE = "movie";
    public static final String KEY_ROLE_MODEL = "roleModel";
    public static final String KEY_QUOTE = "liveQuote";
    public static final String KEY_HOBBY = "hobbies";
    public static final String KEY_CRAZY = "crazyAbout";
    public static final String KEY_FASCINATE = "fascinatedBy";
    public static final String KEY_AMBITION = "aimTobe";
    public static final String KEY_GENIE = "genie";
    public static final String KEY_PRESIDENT = "rulePresident";
    public static final String KEY_BAD_AT = "badAt";
    public static final String KEY_LOVE = "describeLove";
    public static final String KEY_FRIENDSHIP = "describeFriendship";
    public static final String KEY_ABOUT_YOU = "aboutYou";


    public SlamsDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                KEY_NAME + " TEXT, " +
                KEY_NICKNAME + " TEXT, " +
                KEY_BORN_ON + " TEXT, " +
                KEY_ZODIAC + " TEXT, " +
                KEY_EMAIL + " TEXT, " +
                KEY_PHONE_NUMBER + " TEXT, " +
                KEY_RELATIONSHIP + " TEXT, " +
                KEY_WORDS + " TEXT, " +
                KEY_COLOR + " TEXT, " +
                KEY_FOOD + " TEXT, " +
                KEY_PLACE + " TEXT, " +
                KEY_SPORT + " TEXT, " +
                KEY_MOVIE_STAR + " TEXT, " +
                KEY_CARTOON_CHAR + " TEXT, " +
                KEY_SINGER + " TEXT, " +
                KEY_MOVIE + " TEXT, " +
                KEY_ROLE_MODEL + " TEXT, " +
                KEY_QUOTE + " TEXT, " +
                KEY_HOBBY + " TEXT, " +
                KEY_CRAZY + " TEXT, " +
                KEY_FASCINATE + " TEXT, " +
                KEY_AMBITION + " TEXT, " +
                KEY_GENIE + " TEXT, " +
                KEY_PRESIDENT + " TEXT, " +
                KEY_BAD_AT + " TEXT, " +
                KEY_LOVE + " TEXT, " +
                KEY_FRIENDSHIP + " TEXT, " +
                KEY_ABOUT_YOU + " TEXT " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addSlams(SlamsModel slam) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, slam.goodName);
        values.put(KEY_NICKNAME, slam.knownAs);
        values.put(KEY_BORN_ON, slam.bornOn);
        values.put(KEY_ZODIAC, slam.zodiacSign);
        values.put(KEY_EMAIL, slam.mailID);
        values.put(KEY_PHONE_NUMBER, slam.phoneNumber);
        values.put(KEY_RELATIONSHIP, slam.relationshipStatus);
        values.put(KEY_WORDS, slam.words);
        values.put(KEY_COLOR, slam.favColor);
        values.put(KEY_FOOD, slam.favFood);
        values.put(KEY_PLACE, slam.favPlace);
        values.put(KEY_SPORT, slam.favSport);
        values.put(KEY_MOVIE_STAR, slam.favMovieStar);
        values.put(KEY_CARTOON_CHAR , slam.favCartoonChar);
        values.put(KEY_SINGER, slam.favBandOrSinger);
        values.put(KEY_MOVIE , slam.favMovie);
        values.put(KEY_ROLE_MODEL, slam.roleModel);
        values.put(KEY_QUOTE, slam.liveQuote);
        values.put(KEY_HOBBY, slam.hobby);
        values.put(KEY_CRAZY, slam.crazyAbout);
        values.put(KEY_FASCINATE, slam.fascinatedBy);
        values.put(KEY_AMBITION, slam.ambition);
        values.put(KEY_GENIE, slam.genie);
        values.put(KEY_PRESIDENT, slam.rulePresident);
        values.put(KEY_BAD_AT, slam.badAt);
        values.put(KEY_LOVE, slam.describeLove);
        values.put(KEY_FRIENDSHIP, slam.describeFriendship);
        values.put(KEY_ABOUT_YOU, slam.aboutYou);

        sqLiteDatabase.insert(TABLE_NAME, null, values);

    }

    public ArrayList<SlamsModel> fetchSlams() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME , null);

        ArrayList<SlamsModel> arrSlams = new ArrayList<>();

        while (cursor.moveToNext()) {

            SlamsModel slamsmodel =  new SlamsModel(-1, false, " ","","",
                    " ", "","",",", "",
                    "","", ",","","","",
                    "","","", "","","",
                    "","","","","","",
                    ""," ");

            slamsmodel.id = cursor.getInt(0);
            slamsmodel.goodName = cursor.getString(1);
            slamsmodel.knownAs = cursor.getString(2);
            slamsmodel.bornOn = cursor.getString(3);
            slamsmodel.zodiacSign = cursor.getString(4);
            slamsmodel.mailID = cursor.getString(5);
            slamsmodel.phoneNumber = cursor.getString(6);
            slamsmodel.relationshipStatus = cursor.getString(7);
            slamsmodel.words = cursor.getString(8);
            slamsmodel.favColor = cursor.getString(9);
            slamsmodel.favFood = cursor.getString(10);
            slamsmodel.favPlace = cursor.getString(11);
            slamsmodel.favSport = cursor.getString(12);
            slamsmodel.favMovieStar = cursor.getString(13);
            slamsmodel.favCartoonChar = cursor.getString(14);
            slamsmodel.favBandOrSinger = cursor.getString(15);
            slamsmodel.favMovie = cursor.getString(16);
            slamsmodel.roleModel = cursor.getString(17);
            slamsmodel.liveQuote = cursor.getString(18);
            slamsmodel.hobby = cursor.getString(19);
            slamsmodel.crazyAbout = cursor.getString(20);
            slamsmodel.fascinatedBy = cursor.getString(21);
            slamsmodel.ambition = cursor.getString(22);
            slamsmodel.genie = cursor.getString(23);
            slamsmodel.rulePresident = cursor.getString(24);
            slamsmodel.badAt = cursor.getString(25);
            slamsmodel.describeLove = cursor.getString(26);
            slamsmodel.describeFriendship = cursor.getString(27);
            slamsmodel.aboutYou = cursor.getString(28);

            arrSlams.add(slamsmodel);
        }
        return arrSlams;
    }
    public Integer deleteSlam(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, KEY_ROW_ID +"=?", new String[]{String.valueOf(id)});
    }

}
