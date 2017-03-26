package myapps.wycoco.com.yourfaceseemsattendance.TeacherSide;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import myapps.wycoco.com.yourfaceseemsattendance.R;

/**
 * Created by dell on 3/26/2017.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {


    public TimePickerFragment(){

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        return  new TimePickerDialog(getActivity(), this, hour, min, DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

        EditText timeStart = (EditText) getActivity().findViewById(R.id.subjectTimeStart);
        EditText timeEnd = (EditText) getActivity().findViewById(R.id.subjectTimeEnd);
        timeStart.setText(String.valueOf(hourOfDay)+" : " + String.valueOf(minute)+"");
        timeEnd.setText(String.valueOf(hourOfDay)+ " : " + String.valueOf(minute)+ "");

    }
}
