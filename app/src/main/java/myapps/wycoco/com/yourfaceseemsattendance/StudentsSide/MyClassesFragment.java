package myapps.wycoco.com.yourfaceseemsattendance.StudentsSide;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import myapps.wycoco.com.yourfaceseemsattendance.Adapters.SubjectsAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyClassesFragment extends Fragment {

    SubjectModel sm;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataReference;
    private FirebaseAuth mFirebaseAuth;


    ArrayList<SubjectModel> subjects = new ArrayList<>();
    RecyclerView recyclerview;
    SubjectsAdapter mAdapter;
    String key;

    public MyClassesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_my_classes, container, false);
        Firebase.setAndroidContext(getApplicationContext());

        mDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDataReference = mDatabase.getReference();




        recyclerview = (RecyclerView)view.findViewById(R.id.recyclerViewMyClasses);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(lm);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new SubjectsAdapter(getApplicationContext(), subjects);
        recyclerview.setAdapter(mAdapter);

        if(key != null)

        {
            FirebaseUser user = mFirebaseAuth.getCurrentUser();
            key = getArguments().getString("enrollKey");
            Toast.makeText(getApplicationContext(), "Enrol Key: " + key , Toast.LENGTH_SHORT).show();
            //fragment_attendees for the subjects



            final Query query = mDataReference.child("Class").child(user.getUid()).child("subjectKey");
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot.exists()) {

                            if (dataSnapshot.getValue().equals(sm.getSubjectKey())) {

                                query.addChildEventListener(new ChildEventListener() {
                                    @Override
                                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

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
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid enrollment key!", Toast.LENGTH_SHORT).show();
                            }


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

        return view;
    }


}
