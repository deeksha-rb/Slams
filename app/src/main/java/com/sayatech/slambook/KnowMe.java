package com.sayatech.slambook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Objects;

public class KnowMe extends Fragment implements DatePickerDialog.OnDateSetListener {

    View view;
    TextInputEditText goodName,knownAs,bornOn,zodiacSign,mailID,phoneNumber,words;
    AutoCompleteTextView relationshipStatus;
    GetToKnowData getToKnowInterface;

    String[] items = {"Single" ,"In a relationship" ,"Married" ,"Divorced" ,"Domestic relationship"};
    ArrayAdapter<String> stringArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_know_me, container, false);

        goodName = view.findViewById(R.id.textName);
        knownAs = view.findViewById(R.id.textFondly);
        bornOn = view.findViewById(R.id.textBorn);
        zodiacSign = view.findViewById(R.id.textZodiac);
        mailID = view.findViewById(R.id.textEmailId);
        phoneNumber = view.findViewById(R.id.textPhoneNumber);
        relationshipStatus = view.findViewById(R.id.textRelationship);
        words = view.findViewById(R.id.textWords);
        stringArrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.list_relationship, items);

        relationshipStatus.setAdapter(stringArrayAdapter);

        bornOn.setOnClickListener(v -> {
            DatePickerClass datePicker = new DatePickerClass();
            datePicker.setListeningActivity(KnowMe.this);
            datePicker.show(requireActivity().getSupportFragmentManager(), "date");
        });
        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR ,year);
        calendar.set(Calendar.MONTH ,month);
        calendar.set(Calendar.DATE , dayOfMonth);
        String dateString = DateFormat.getDateInstance().format(calendar.getTime());

        bornOn.setText(dateString);
    }

    public interface GetToKnowData {
        void onGetToKnowData(SlamsModel model);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof GetToKnowData) {
            getToKnowInterface = (GetToKnowData) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getToKnowInterface = null;
    }

    public boolean passingData() {
        String GoodName = Objects.requireNonNull(goodName.getText()).toString().trim();
        String FondlyKnown = Objects.requireNonNull(knownAs.getText()).toString().trim();
        String BornOn = Objects.requireNonNull(bornOn.getText()).toString().trim();
        String ZodiacSign = Objects.requireNonNull(zodiacSign.getText()).toString().trim();
        String EMailID = Objects.requireNonNull(mailID.getText()).toString().trim();
        String PhoneNumber = Objects.requireNonNull(phoneNumber.getText()).toString().trim();
        String RelationshipStatus = relationshipStatus.getText().toString().trim();
        String Words = Objects.requireNonNull(words.getText()).toString().trim();

        if (GoodName.isEmpty() | FondlyKnown.isEmpty() | BornOn.isEmpty() |
                ZodiacSign.isEmpty() | EMailID.isEmpty() | PhoneNumber.isEmpty() |
                RelationshipStatus.isEmpty() | Words.isEmpty()) {
            return false;
        } else {
            SlamsModel slam = new SlamsModel(
                    -1,
                    false,
                    GoodName,
                    FondlyKnown,
                    BornOn,
                    ZodiacSign,
                    EMailID,
                    PhoneNumber,
                    RelationshipStatus,
                    Words,
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
                    "",
                    ""
            );
            getToKnowInterface.onGetToKnowData(slam);
            return true;
        }
    }
}
