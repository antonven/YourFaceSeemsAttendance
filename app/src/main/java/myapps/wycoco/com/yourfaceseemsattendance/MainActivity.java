package myapps.wycoco.com.yourfaceseemsattendance;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import myapps.wycoco.com.yourfaceseemsattendance.Adapters.SubjectsAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;

public class MainActivity extends AppCompatActivity {

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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }




        mDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDataReference = mDatabase.getReference().child("user");
//        mroot = new Firebase("https://yourfaceseemsattendance-517c5.firebaseio.com/Subject");


//        welcomeTxt = (TextView)findViewById(R.id.welcomeTxt);
//        nameTxt = (TextView)findViewById(R.id.nameText);
        floatingButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);



        floatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSubjectFragment add = new AddSubjectFragment();
                getFragmentManager().
                     beginTransaction().add(R.id.frame1, add).commit();
            }
        });

        //recyclerview for the subjects
        mDataReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.exists()){
                    sm = dataSnapshot.getValue(SubjectModel.class);
                    subjects.add(sm);

                    mAdapter = new SubjectsAdapter(getApplicationContext(), subjects);
                    recyclerview = (RecyclerView)findViewById(R.id.recyclerView);
                    RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
                    recyclerview.setLayoutManager(lm);
                    recyclerview.setItemAnimator(new DefaultItemAnimator());
                    recyclerview.setAdapter(mAdapter);
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



        mDataReference = mDatabase.getReference("Subject");


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    //user is signed in
                    onSignedInInitialize(user.getDisplayName());
                    mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
                }
                else{
                    //user is signed out
                    onSignedOutCleanup();
                }
            }
        };
    }


    @Override
    protected void onStart() {
        super.onStart();

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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    //this is the code for signing in + getting the name and put it into the firebase.
    private void onSignedInInitialize(String username){
        mUsername = username;

        mDataReference.push().setValue(username);
        Toast.makeText(MainActivity.this, "Welcome " + username + " !", Toast.LENGTH_LONG).show();


    }

    //
    private void onSignedOutCleanup(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())).setTheme(R.style.AppTheme)
                        .build(),
                RC_SIGN_IN);
    }
}
