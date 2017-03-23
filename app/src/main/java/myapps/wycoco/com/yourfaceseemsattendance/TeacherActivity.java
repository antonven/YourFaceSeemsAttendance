package myapps.wycoco.com.yourfaceseemsattendance;

import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Adapters.SubjectsAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;

public class TeacherActivity extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;

    private String mUsername;
    SubjectModel sm;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataReference;
    private FirebaseAuth mFirebaseAuth;
    ChildEventListener mChildEventListener;

    FloatingActionButton floatingButton;
    ArrayList<SubjectModel> subjects = new ArrayList<>();
    RecyclerView recyclerview;
    SubjectsAdapter mAdapter;
    Firebase mroot;

    public TeacherActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_classes);



        mDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDataReference = mDatabase.getReference("Class");


        floatingButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubjectFragment add = new AddSubjectFragment();
                getFragmentManager().
                        beginTransaction().add(R.id.frame2, add).addToBackStack("hey").commit();
            }
        });


        recyclerview = (RecyclerView)findViewById(R.id.recyclerViewTeacher);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(lm);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new SubjectsAdapter(getApplicationContext(), subjects);
        recyclerview.setAdapter(mAdapter);


        //recyclerview for the subjects
       mDataReference.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               if(dataSnapshot.exists()){
                   sm = dataSnapshot.getValue(SubjectModel.class);
                   subjects.add(sm);
                   Log.e("dd",""+sm.getSubjectName());
                    mAdapter.notifyDataSetChanged();
               }
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

}

