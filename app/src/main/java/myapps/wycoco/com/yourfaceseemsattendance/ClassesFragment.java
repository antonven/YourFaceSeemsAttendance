package myapps.wycoco.com.yourfaceseemsattendance;


import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Adapters.SubjectsAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassesFragment extends Fragment {


    public ClassesFragment() {
        // Required empty public constructor
    }

    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;

    private String mUsername;
    SubjectModel sm;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataReference;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private ChildEventListener mChildEventListener;
    private SubjectsAdapter subjectsAdapter;


    TextView welcomeTxt, nameTxt;
    FloatingActionButton floatingButton;
    FragmentManager fm;
    ArrayList<SubjectModel> subjects;
    RecyclerView recyclerview;
    SubjectsAdapter mAdapter;
    Firebase mroot;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_classes, container, false);
        Firebase.setAndroidContext(getApplicationContext());



        mDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mroot = new Firebase("https://yourfaceseemsattendance-517c5.firebaseio.com/Subject");
        mDataReference = mDatabase.getReference("Subject");


        floatingButton = (FloatingActionButton)view.findViewById(R.id.floatingActionButton);
        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubjectFragment add = new AddSubjectFragment();
                getFragmentManager().
                        beginTransaction().add(R.id.frame2, add).addToBackStack("hey").commit();
            }
        });


        //recyclerview for the subjects
        mroot.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()){
                    sm = dataSnapshot.getValue(SubjectModel.class);
                    subjects.add(sm);
                    Log.e("dd",""+sm.getSubjectName());

                    start(view);

                }
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        return view;
    }


    public void start(View rootView){


        recyclerview = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(rootView.getContext());
        recyclerview.setLayoutManager(lm);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new SubjectsAdapter(rootView.getContext(), subjects);
        Log.e("dd",""+sm.getSubjectKey());
        recyclerview.setAdapter(mAdapter);

    }

}
