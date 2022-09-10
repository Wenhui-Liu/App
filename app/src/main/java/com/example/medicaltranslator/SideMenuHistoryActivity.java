package com.example.medicaltranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.medicaltranslator.databinding.SideMenuHistoryBinding;

public class SideMenuHistoryActivity extends SideMenuBaseActivity {

    SideMenuHistoryBinding sideMenuHistoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideMenuHistoryBinding = SideMenuHistoryBinding.inflate(getLayoutInflater());
        setContentView(sideMenuHistoryBinding.getRoot());
        allocateActivityTitle("History");
    }
}