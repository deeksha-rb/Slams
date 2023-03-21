package com.sayatech.slambook;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerClass extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog.OnDateSetListener m_listener = null;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DATE);
        return new DatePickerDialog(getActivity(),
                this,
                year ,month ,date);
    }

    public void setListeningActivity(DatePickerDialog.OnDateSetListener listener)
    {
        m_listener = listener;
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (m_listener != null) {
            m_listener.onDateSet(view, year, month, dayOfMonth);
        }
    }
}
