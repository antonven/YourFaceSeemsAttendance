package myapps.wycoco.com.yourfaceseemsattendance.TeacherSide;


import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;


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

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_subject, container,false);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        subjectName = (EditText)view.findViewById(R.id.subjectName);
        subjectRoom = (EditText)view.findViewById(R.id.subjectRoom);
        subjectTimeStart = (EditText)view.findViewById(R.id.subjectTimeStart);
        subjectTimeEnd = (EditText)view.findViewById(R.id.subjectTimeEnd);
        subjectDate = (EditText)view.findViewById(R.id.subjectDate);
        subjectKey = (EditText)view.findViewById(R.id.subjectKey);
        addSubject = (Button)view.findViewById(R.id.addSubjectBtn);


        subjectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dateFrag = new DatePickerFragment();
                dateFrag.show(getFragmentManager(), "hey");
            }
        });

        subjectTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timeFrag = new TimePickerFragment();
                timeFrag.show(getFragmentManager(), "hoy");
            }
        });
        subjectTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timeFrag = new TimePickerFragment();
                timeFrag.show(getFragmentManager(), "wow");
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

                //add tanan values sa firebase
                SubjectModel sm = new SubjectModel(subName, roomNum, subteacher, tStart, tEnd, date, skey);
                reference.child("Class").push().setValue(sm);
                startActivity(new Intent(getActivity(), TeacherActivity.class));
            }
        });


        return view;
    }



}
