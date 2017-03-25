package myapps.wycoco.com.yourfaceseemsattendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by dell on 3/21/2017.
 */

public class DatePickerFragment extends android.app.DialogFragment implements DatePickerDialog.OnDateSetListener{

    int year, month, day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_WEEK);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }




    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        year = this.year;
        month = this.month;
        day = dayOfMonth;


    }

}
