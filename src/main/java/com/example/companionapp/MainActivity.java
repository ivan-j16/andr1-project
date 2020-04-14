package com.example.companionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.companionapp.AsyncTasks.BuildingsJSONTask;
import com.example.companionapp.AsyncTasks.PeopleJSONTask;
import com.example.companionapp.AsyncTasks.ScheduleJSONTask;
import com.example.companionapp.Fragments.BuildingsFragment;
import com.example.companionapp.Fragments.HomeFragment;
import com.example.companionapp.Fragments.PeopleFragment;
import com.example.companionapp.Fragments.ScheduleFragment;
import com.example.companionapp.Resources.Building;
import com.example.companionapp.Resources.Person;
import com.example.companionapp.Resources.Schedule;
import com.google.android.material.navigation.NavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnFragmentInteractionListener, PeopleFragment.OnFragmentInteractionListener, ScheduleFragment.OnFragmentInteractionListener, BuildingsFragment.OnFragmentInteractionListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private List<Person> people;
    private List<Schedule> schedule;
    private List<Building> buildings;

    private String TOKEN = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        TOKEN = getIntent().getStringExtra("TOKEN");

        init();

        loadTasks();
        loadSchedule();
        loadBuildings();
    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void loadTasks() {
        // Load person list
        PeopleJSONTask peopleTask = new PeopleJSONTask();
        peopleTask.execute(TOKEN);
        people = peopleTask.getPersons();
    }

    private void loadSchedule() {
        // Load schedule list
        ScheduleJSONTask scheduleJSONTask = new ScheduleJSONTask(this);
        scheduleJSONTask.execute(TOKEN);
        schedule = scheduleJSONTask.getSchedule();
    }


    private void loadBuildings() {
        // Load building list
        BuildingsJSONTask buildingsJSONTask = new BuildingsJSONTask();
        buildingsJSONTask.execute(TOKEN);
        buildings = buildingsJSONTask.getBuildings();
    }

    // Implicit intent
    public void goFontys(View view)  {
        String url="https://fontys.edu/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        this.startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                else{
                    return false;
                }
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean isValidDestination(int fragmentId){
        // Check if the user is currently viewing not the fragment
        return fragmentId != Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_people: {
                if (isValidDestination(R.id.peopleScreen)){
                    //If the user is not viewing the fragment, navigate to it
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.peopleScreen);
                }
                break;
            }
            case R.id.nav_schedule:{
                if (isValidDestination(R.id.scheduleScreen)){
                    //If the user is not viewing the fragment, navigate to it
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.scheduleScreen);
                }
                break;
            }
            case R.id.nav_buildings:{
                if (isValidDestination(R.id.buildingsScreen)){
                    //If the user is not viewing the fragment, navigate to it
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.buildingsScreen);
                }
                break;
            }
            case R.id.nav_home:{
                // Clear back stack whenever the user navigates to home
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.main, true)
                        .build();

                // Navigate to home
                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.homeScreen, null, navOptions);
                break;
            }
            case R.id.nav_logout:{
                logout();
            }
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout() {
        // Make TOKEN an empty string, delete all cookies and go to Login activity
        android.webkit.CookieManager.getInstance().removeAllCookie();
        TOKEN = "";
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public String getToken() { return TOKEN; }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment), drawerLayout);
    }

    // Home Listener
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

