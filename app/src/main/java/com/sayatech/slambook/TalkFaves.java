package com.sayatech.slambook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

public class TalkFaves extends Fragment {

    View view;
    TextInputEditText favColor,favFood,favPlace,favSport,favMovieStar,favCartoonChar,favBandOrSinger,
                        favMovie,roleModel,liveQuote;
    TalkFavesData talkFavesInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_talk_faves, container, false);

        favColor = view.findViewById(R.id.textColor);
        favFood = view.findViewById(R.id.textFood);
        favPlace = view.findViewById(R.id.textPlace);
        favSport = view.findViewById(R.id.textSport);
        favMovieStar = view.findViewById(R.id.textMovieStar);
        favCartoonChar = view.findViewById(R.id.textCartoon);
        favBandOrSinger = view.findViewById(R.id.textSinger);
        favMovie = view.findViewById(R.id.textMovie);
        roleModel = view.findViewById(R.id.textRoleModel);
        liveQuote = view.findViewById(R.id.textQuote);

        return view;
    }
    public interface TalkFavesData {
        void onTalkFavesData(SlamsModel model);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof TalkFavesData){
            talkFavesInterface = (TalkFavesData) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        talkFavesInterface = null;
    }

    public boolean passingData(){
        String Color = favColor.getText().toString().trim();
        String Food = favFood.getText().toString().trim();
        String Place = favPlace.getText().toString().trim();
        String Sport = favSport.getText().toString().trim();
        String MovieStar = favMovieStar.getText().toString().trim();
        String CartoonChar = favCartoonChar.getText().toString().trim();
        String BandSinger = favBandOrSinger.getText().toString().trim();
        String Movie = favMovie.getText().toString().trim();
        String RoleModel = roleModel.getText().toString().trim();
        String LiveQuote = liveQuote.getText().toString().trim();

        if ( Color.isEmpty() | Food.isEmpty() | Place.isEmpty() | Sport.isEmpty() |
                MovieStar.isEmpty() | CartoonChar.isEmpty() | BandSinger.isEmpty() |
                Movie.isEmpty() | RoleModel.isEmpty() | LiveQuote.isEmpty()){
            return false;
        } else {
            SlamsModel slam = new SlamsModel(
                    -1,
                    false,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    Color,
                    Food,
                    Place,
                    Sport,
                    MovieStar,
                    CartoonChar,
                    BandSinger,
                    Movie,
                    RoleModel,
                    LiveQuote,
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""
            );
            talkFavesInterface.onTalkFavesData(slam);
            return true;
        }
    }
}