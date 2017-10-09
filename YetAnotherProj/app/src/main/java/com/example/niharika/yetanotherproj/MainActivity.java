package com.example.niharika.yetanotherproj;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.niharika.yetanotherproj.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString(Config.Uname_SHARED_PREF,"Not Available");

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        tabOne.setText("EVENTS" );
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_event, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        tabTwo.setText("CONTACTS" );
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_contact, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);



        TextView tabFour = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        tabFour.setText("MENU CARD" );
        tabFour.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_menu, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabFour);

        TextView tabFive = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        tabFive.setText("OFFERS" );
        tabFive.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_offers, 0, 0);
        tabLayout.getTabAt(3).setCustomView(tabFive);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tabs, null);
        tabThree.setText("FEEDBACK" );
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_polling, 0, 0);
        tabLayout.getTabAt(4).setCustomView(tabThree);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new FiveFragment(), "EVENTS");
        adapter.addFrag(new TwoFragment(), "CONTACTS" );
        adapter.addFrag(new FourFragment(), "MENU CARD" );
        adapter.addFrag(new OneFragment(), "OFFERS");
        adapter.addFrag(new ThreeFragment(), "FEEDBACK");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        else if (id == R.id.action_aboutus)
        {
            Intent i= new Intent(this,Aboutus.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout(){
//Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

//Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

//Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

//Putting blank value to email
                        editor.putString(Config.Uname_SHARED_PREF, "");

//Saving the sharedpreferences
                        editor.commit();

//Starting login activity
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

//Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
