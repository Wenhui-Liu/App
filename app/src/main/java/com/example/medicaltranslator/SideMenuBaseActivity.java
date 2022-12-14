package com.example.medicaltranslator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class SideMenuBaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout side_menu_draw_layout;
    NavigationView menu_navigationView;
    //set the side menu content
    @Override
    public void setContentView(View view) {
        side_menu_draw_layout = (DrawerLayout)getLayoutInflater().inflate(R.layout.side_menu_base,null);
        FrameLayout side_menu_container = side_menu_draw_layout.findViewById(R.id.side_menu_activity_container);
        side_menu_container.addView(view);
        super.setContentView(side_menu_draw_layout);

        //set the same toolbar
        Toolbar toolbar = side_menu_draw_layout.findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
//        TextView toolbarTitle = (TextView) toolbar.findViewById(R.id.main_toolbar_title);
//        toolbarTitle.setText("Medical Translator App");
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        menu_navigationView = side_menu_draw_layout.findViewById(R.id.nav_menu_view);
        menu_navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,side_menu_draw_layout,toolbar,R.string.draw_open,R.string.draw_close);
        side_menu_draw_layout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //select different item
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        side_menu_draw_layout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()) {
            case R.id.nav_account:
                startActivity(new Intent(this, SideMenuAccountActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_saved:
                startActivity(new Intent(this, SideMenuSavedActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_history:
                startActivity(new Intent(this, SideMenuHistoryActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_setting:
                startActivity(new Intent(this, SideMenuSettingActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_help:
                startActivity(new Intent(this, SideMenuHelpActivity.class));
                overridePendingTransition(0,0);
                break;
            case R.id.nav_logout:
                logout(this);
        }
        return false;
    }

    //set each item's own title
    protected void allocateActivityTitle(String title_string) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title_string);
        }
    }

    //logout function
    public static void logout(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Log Out");
        builder.setMessage("Are you sure you want to logout ?");
        //close app
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                activity.finishAffinity();
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }
}