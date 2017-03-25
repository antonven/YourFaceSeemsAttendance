package myapps.wycoco.com.yourfaceseemsattendance;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Models.TeacherModel;
import myapps.wycoco.com.yourfaceseemsattendance.StudentsSide.StudentsActivity;

/**
 * Created by dell on 3/25/2017.
 */

public class LoginFragment extends Fragment {


    ImageView student, teacher;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mReferenceTeacher, mReferenceStudent;
    FirebaseAuth firebaseAuth;
    TeacherModel teachers;
    ArrayList<TeacherModel> tm = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        mReferenceTeacher = firebaseDatabase.getReference().child("Teacher");

        mReferenceStudent = firebaseDatabase.getReference().child("Student");
        student = (ImageView)view.findViewById(R.id.studentLogin);
        teacher = (ImageView)view.findViewById(R.id.teacherLogin);
        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();



        student.setOnClickListener(new View.OnClickListener() {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            @Override
            public void onClick(View view) {
                mReferenceStudent.push().setValue(user.getDisplayName());
                startActivity(new Intent(getActivity(), StudentsActivity.class));

                mReferenceStudent.addChildEventListener(new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Toast.makeText(getActivity(), "Welcome " + user.getDisplayName() + " !", Toast.LENGTH_SHORT).show();
                        Log.e("AW", "onAuthStateChanged:signed_in:" + user.getUid());

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            @Override
            public void onClick(View view) {

                mReferenceTeacher.push().setValue(user.getDisplayName());

                Log.e("AW", "onAuthStateChanged:signed_in:" + user.getUid());
                startActivity(new Intent(getActivity(), FaceDetectorActivity.class));

                mReferenceTeacher.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        Toast.makeText(getActivity(), "Welcome " + user.getDisplayName() + " !", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });


        return view;
    }



}


