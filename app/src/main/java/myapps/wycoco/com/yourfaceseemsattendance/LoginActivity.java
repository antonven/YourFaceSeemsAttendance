package myapps.wycoco.com.yourfaceseemsattendance;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


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
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;

public class LoginActivity extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;

    private String mUsername;
    SubjectModel sm;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataReference;
    private DatabaseReference mDataUser;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Firebase.setAndroidContext(getApplicationContext());



        mDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mroot = new Firebase("https://yourfaceseemsattendance-517c5.firebaseio.com/Subject");
        mDataUser = mDatabase.getReference("Teacher");
        mDataReference = mDatabase.getReference("Subject");


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null && user.getUid() != mDataUser.getKey()){
                    //user is signed in
                    mDataUser.push().setValue(user.getDisplayName());
                    Toast.makeText(LoginActivity.this, "Welcome " + user.getDisplayName() + " !", Toast.LENGTH_SHORT).show();
                    Log.e("AW", "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(LoginActivity.this, TeacherActivity.class));
                }
                else if(user != null && user.getUid() == mDataUser.getKey()){
                    Toast.makeText(LoginActivity.this, "Shit  " + user.getDisplayName() + " !", Toast.LENGTH_SHORT).show();
                    Log.e("AW", "onAuthStateChanged:signed_in:" + user.getUid());
                }
                else{
                    //user is signed out
                    onSignedOutCleanup();
                }
            }
        };







        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(LoginActivity.this, "key "+  dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
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
        };
        mDataReference.addChildEventListener(mChildEventListener);


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


    //
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
