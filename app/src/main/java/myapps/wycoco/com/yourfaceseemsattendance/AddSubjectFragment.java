package myapps.wycoco.com.yourfaceseemsattendance;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TimePicker;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.TimePickerDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSubjectFragment extends Fragment {


    public AddSubjectFragment() {
        // Required empty public constructor
    }
    FirebaseDatabase database;
    DatabaseReference reference;

    EditText subjectName, subjectTime, subjectDate, subjectRoom;
    Button publishSubject;
    TimePicker time, date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_subject, container,false);

        final Calendar calendar = Calendar.getInstance();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Subject");

        subjectName = (EditText)view.findViewById(R.id.subjectName1);
        subjectRoom = (EditText)view.findViewById(R.id.subjectRoom);
        subjectTime = (EditText)view.findViewById(R.id.subjectTime);
        subjectDate = (EditText)view.findViewById(R.id.subjectDate);

        publishSubject = (Button)view.findViewById(R.id.btnPublish);
        time = (TimePicker)view.findViewById(R.id.timePicker);


        subjectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timeFrag = new TimePickerDialog();

            }
        });

        publishSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subName = subjectName.getText().toString();
                String roomNum = subjectRoom.getText().toString();
//                String time = getArguments().getString("subject time");
                String date = subjectDate.getText().toString();

                SubjectModel sm = new SubjectModel(subName, roomNum, date);
                reference.push().setValue(sm);

                startActivity(new Intent(view.getContext(), MainActivity.class));

            }
        });


        return view;
    }

}
