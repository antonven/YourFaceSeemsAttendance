package myapps.wycoco.com.yourfaceseemsattendance.TeacherSide;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.Firebase;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;

import myapps.wycoco.com.yourfaceseemsattendance.Adapters.SubjectsAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Login.LoginActivity;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;

public class TeacherActivity extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;


    SubjectModel sm;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataReference;
    private FirebaseAuth mFirebaseAuth;

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
        mDataReference = mDatabase.getReference().child("Class");


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


        //fragment_attendees for the subjects
       mDataReference.addChildEventListener(new ChildEventListener() {
           FirebaseUser user = mFirebaseAuth.getCurrentUser();
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               if(dataSnapshot.exists()){
                   sm = dataSnapshot.getValue(SubjectModel.class);
                   if(sm.getSubjectTeacher().equals(user.getDisplayName())){
                       subjects.add(sm);
                       Log.e("dd",""+sm.getSubjectName());
                       mAdapter.notifyDataSetChanged();
                   }
                   else{

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.sign_out:
                //sign out
                AuthUI.getInstance().signOut(this);
                startActivity(new Intent(TeacherActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void onSignedOutCleanup(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())).setTheme(R.style.FirebaseUI)
                        .build(),
                RC_SIGN_IN);
    }
}

