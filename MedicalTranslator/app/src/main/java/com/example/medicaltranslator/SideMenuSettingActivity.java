package com.example.medicaltranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.medicaltranslator.databinding.SideMenuSettingBinding;

public class SideMenuSettingActivity extends SideMenuBaseActivity {

   SideMenuSettingBinding sideMenuSettingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideMenuSettingBinding = SideMenuSettingBinding.inflate(getLayoutInflater());
        setContentView(sideMenuSettingBinding.getRoot());
        allocateActivityTitle("Settings");
    }
}