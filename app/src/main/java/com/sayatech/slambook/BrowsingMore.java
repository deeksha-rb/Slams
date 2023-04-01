package com.sayatech.slambook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class BrowsingMore extends Fragment {

    View view;
    Button save;
    TextInputEditText hobby,crazyAbout,fascinatedBy,
                      ambition,genie,rulePresident,badAt,
                      describeLove,describeFriendship,aboutYou;
    BrowsingMoreData browsingMoreInterface;
    SlamsDataBase slamsDataBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_browsing_more, container, false);

        hobby = view.findViewById(R.id.textHobbies);
        crazyAbout = view.findViewById(R.id.textCrazy);
        fascinatedBy = view.findViewById(R.id.textFascinate);
        ambition = view.findViewById(R.id.textAmbition);
        genie = view.findViewById(R.id.textGenie);
        rulePresident = view.findViewById(R.id.textPresident);
        badAt = view.findViewById(R.id.textBadAt);
        describeLove = view.findViewById(R.id.textLove);
        describeFriendship = view.findViewById(R.id.textFriendship);
        aboutYou = view.findViewById(R.id.textAboutYou);

        save = view.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {

                String Hobbies = hobby.getText().toString().trim();
                String CrazyAbout = crazyAbout.getText().toString().trim();
                String FascinatedBy = fascinatedBy.getText().toString().trim();
                String Ambition = ambition.getText().toString().trim();
                String Genie = genie.getText().toString().trim();
                String RulePresident = rulePresident.getText().toString().trim();
                String BadAt = badAt.getText().toString().trim();
                String DescribeLove = describeLove.getText().toString().trim();
                String DescribeFriendship = describeFriendship.getText().toString().trim();
                String AboutYou = aboutYou.getText().toString().trim();

                if (Hobbies.isEmpty() | CrazyAbout.isEmpty() | FascinatedBy.isEmpty() |
                        Ambition.isEmpty() | Genie.isEmpty() | RulePresident.isEmpty() |
                        BadAt.isEmpty() | DescribeLove.isEmpty() |
                            DescribeFriendship.isEmpty()|AboutYou.isEmpty())
                {
                    Toast.makeText(getActivity(), "Please enter all the fields to save the slam"
                            , Toast.LENGTH_SHORT).show();
                }else{
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
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            Hobbies,
                            CrazyAbout,
                            FascinatedBy,
                            Ambition,
                            Genie,
                            RulePresident,
                            BadAt,
                            DescribeLove,
                            DescribeFriendship,
                            AboutYou
                    );
                    browsingMoreInterface.onBrowsingMoreData(slam);
                }
            }
        });
        return view;
    }
    public interface BrowsingMoreData{
        void onBrowsingMoreData(SlamsModel model);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof BrowsingMoreData){
            browsingMoreInterface = (BrowsingMoreData) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        browsingMoreInterface = null;
    }
}