package myapps.wycoco.com.yourfaceseemsattendance.StudentsSide;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
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
import java.util.Calendar;

import myapps.wycoco.com.yourfaceseemsattendance.Adapters.MyClassesAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Adapters.SubjectsAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Models.MyClassModel;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyClassesFragment extends Fragment {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataReference;
    private FirebaseAuth mFirebaseAuth;
    CardView cardView;
    MyClassModel mc;
    ArrayList<MyClassModel> classes = new ArrayList<>();
    ArrayList<SubjectModel> subjects = new ArrayList<>();
    RecyclerView recyclerview;
    MyClassesAdapter mAdapter;


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

        Calendar calendar = Calendar.getInstance();
        final String time = calendar.getTime().toString();

        cardView = (CardView)view.findViewById(R.id.classCard);

        recyclerview = (RecyclerView)view.findViewById(R.id.recyclerViewMyClasses);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(lm);
        recyclerview.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new MyClassesAdapter(getApplicationContext(), classes);
        recyclerview.setAdapter(mAdapter);


            final FirebaseUser user = mFirebaseAuth.getCurrentUser();

            //Query all the enrolled classes.
            Query query = mDataReference.child("Users")
            .child(user.getUid())
            .child("MyClasses").orderByKey();
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if(dataSnapshot.exists()) {
                        mc = dataSnapshot.getValue(MyClassModel.class);
                        if(user.getUid().equals(mc.getUserID()))
                        classes.add(mc);
                        mAdapter.notifyDataSetChanged();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No shit", Toast.LENGTH_SHORT).show();
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

        return view;
    }


}
