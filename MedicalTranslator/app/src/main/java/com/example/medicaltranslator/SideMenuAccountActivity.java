package com.example.medicaltranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.medicaltranslator.databinding.SideMenuAccountBinding;

public class SideMenuAccountActivity extends SideMenuBaseActivity {

    SideMenuAccountBinding sideMenuAccountBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideMenuAccountBinding = SideMenuAccountBinding.inflate(getLayoutInflater());
        setContentView(sideMenuAccountBinding.getRoot());
        //set the title name
        allocateActivityTitle("Account");
    }

}