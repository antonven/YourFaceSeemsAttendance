package myapps.wycoco.com.yourfaceseemsattendance.StudentsSide;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;

import myapps.wycoco.com.yourfaceseemsattendance.LoginActivity;
import myapps.wycoco.com.yourfaceseemsattendance.LoginFragment;
import myapps.wycoco.com.yourfaceseemsattendance.R;

public class StudentsActivity extends AppCompatActivity {

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        TabLayout tab = (TabLayout)findViewById(R.id.tabLayout);
        tab.addTab(tab.newTab().setText("Classes"));
        tab.addTab(tab.newTab().setText("My Classes"));

        fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.frameClasses, new ClassesFragment()).commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0)
                    fm.beginTransaction().replace(R.id.frameClasses, new ClassesFragment()).commit();
                else if(tab.getPosition() == 1)
                    fm.beginTransaction().replace(R.id.frameClasses, new MyClassesFragment()).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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
                startActivity(new Intent(StudentsActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
