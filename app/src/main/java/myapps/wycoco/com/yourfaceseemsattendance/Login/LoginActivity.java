package myapps.wycoco.com.yourfaceseemsattendance.Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import myapps.wycoco.com.yourfaceseemsattendance.Adapters.SubjectsAdapter;
import myapps.wycoco.com.yourfaceseemsattendance.Models.SubjectModel;
import myapps.wycoco.com.yourfaceseemsattendance.R;
import myapps.wycoco.com.yourfaceseemsattendance.StudentsSide.StudentsActivity;
import myapps.wycoco.com.yourfaceseemsattendance.TeacherSide.TeacherActivity;

public class LoginActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1;


    private FirebaseDatabase mDatabase;
    private DatabaseReference mDataReference;
    private DatabaseReference mDataUser;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    FragmentManager fm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        Firebase.setAndroidContext(getApplicationContext());


        mDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mDataUser = mDatabase.getReference();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull final FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null ){
                    //user is signed in
                    //iset ni niya ang pangan sa ni login sa users name sa firebase
                    mDataUser.child("Users").child(user.getUid()).child("Name").setValue(user.getDisplayName());
                    //gi query niya ang role na gi pili sa logged in user ngadto sa Login Fragment
                    Query query = mDataUser.child("Users").child(user.getUid()).child("Role").orderByValue();
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //if wa pa gani kapili og role ang user kay mo proceed sha sa LoginFragment
                            if(dataSnapshot.getValue() == null){
                                fm = getFragmentManager();
                                fm.beginTransaction().replace(R.id.frame6, new LoginFragment()).addToBackStack("aw").commit();
                            }
                            //if nakapili na shag role daan kay login na sha diritso sa activity sa iyang role
                            else{
                                if (dataSnapshot.getValue().toString().equals("Teacher")) {

                                    startActivity(new Intent(LoginActivity.this, TeacherActivity.class));
                                    Toast.makeText(LoginActivity.this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                                } else if (dataSnapshot.getValue().toString().equals("Student")) {

                                    startActivity(new Intent(LoginActivity.this, StudentsActivity.class));
                                    Toast.makeText(LoginActivity.this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


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
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
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
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
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

    //para sign out
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
