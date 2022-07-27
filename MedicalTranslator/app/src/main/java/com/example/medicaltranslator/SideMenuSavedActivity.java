package com.example.medicaltranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.medicaltranslator.databinding.SideMenuSavedBinding;

public class SideMenuSavedActivity extends SideMenuBaseActivity {

    SideMenuSavedBinding sideMenuSavedBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideMenuSavedBinding = SideMenuSavedBinding.inflate(getLayoutInflater());
        setContentView(sideMenuSavedBinding.getRoot());
        allocateActivityTitle("Saved Medicines");
    }
}