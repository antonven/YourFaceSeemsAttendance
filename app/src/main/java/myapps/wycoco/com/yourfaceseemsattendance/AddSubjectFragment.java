package myapps.wycoco.com.yourfaceseemsattendance;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.TimePickerDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddSubjectFragment extends Fragment{


    public AddSubjectFragment() {
        // Required empty public constructor
    }
    FirebaseDatabase database;
    DatabaseReference reference;

    EditText subjectName, subjectTimeStart,  subjectRoom, subjectKey, subjectTimeEnd;
    TextView subjectDate;
    Button addSubject;
    TimePicker time, date;
    ArrayList<SubjectModel> subjects;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_subject, container,false);

        final Calendar calendar = Calendar.getInstance();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("Subject");
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        subjectName = (EditText)view.findViewById(R.id.subjectName);
        subjectRoom = (EditText)view.findViewById(R.id.subjectRoom);
        subjectTimeStart = (EditText)view.findViewById(R.id.subjectTimeStart);
        subjectTimeEnd = (EditText)view.findViewById(R.id.subjectTimeEnd);
        subjectDate = (EditText)view.findViewById(R.id.subjectDate);
        subjectKey = (EditText)view.findViewById(R.id.subjectKey);
        addSubject = (Button)view.findViewById(R.id.addSubjectBtn);


        subjectTimeStart.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                DialogFragment dateFrag = new DatePickerFragment();
                dateFrag.show(getFragmentManager(), "hey");


            }
        });

        addSubject.setOnClickListener(new View.OnClickListener() {

            FirebaseUser user = firebaseAuth.getCurrentUser();
            @Override
            public void onClick(View view) {
                String subName = subjectName.getText().toString();
                String roomNum = subjectRoom.getText().toString();
                String subteacher = user.getDisplayName();
                String skey = subjectKey.getText().toString();
                String tStart = subjectTimeStart.getText().toString();
                String tEnd = subjectTimeEnd.getText().toString();
                String date = subjectDate.getText().toString();

                SubjectModel sm = new SubjectModel(subName, roomNum, subteacher, tStart, tEnd, date, skey);
                reference.push().setValue(sm);

                startActivity(new Intent(view.getContext(), MainActivity.class));

            }
        });


        return view;
    }

}
