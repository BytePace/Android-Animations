package com.bytepace.myanimations.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.bytepace.myanimations.R;
import com.bytepace.myanimations.fragments.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setTitle(getString(R.string.select_animation_in_menu));
        drawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_increase_square) {
            replaceFragment(IncreaseSquareFragment.newInstance());
        } else if (id == R.id.nav_transition_square) {
            replaceFragment(TransitionSquareFragment.newInstance());
        } else if (id == R.id.nav_other_animations) {
            replaceFragment(OtherAnimationsFragment.newInstance());
        } else if (id == R.id.nav_contact_sync) {
            startActivity(ContactSyncActivity.newIntent(this));
        } else if (id == R.id.nav_unlock) {
            replaceFragment(UnlockFragment.newInstance());
        } else if (id == R.id.nav_zoom_image) {
            replaceFragment(ZoomImageFragment.newInstance());
        } else if (id == R.id.nav_shake) {
            replaceFragment(ShakeFragment.newInstance());
        } else if (id == R.id.nav_progress_bar) {
            replaceFragment(ProgressBarFragment.newInstance());
        } else if (id == R.id.nav_falling_objects) {
            replaceFragment(FallingObjectsFragment.newInstance());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
