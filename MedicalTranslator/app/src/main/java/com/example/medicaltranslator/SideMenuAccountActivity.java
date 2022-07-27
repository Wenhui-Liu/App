package com.example.medicaltranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;

import com.example.medicaltranslator.databinding.SideMenuAccountBinding;

public class SideMenuAccountActivity extends SideMenuBaseActivity {

    SideMenuAccountBinding sideMenuAccountBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideMenuAccountBinding = SideMenuAccountBinding.inflate(getLayoutInflater());
        setContentView(sideMenuAccountBinding.getRoot());
        allocateActivityTitle("Account");
    }

}