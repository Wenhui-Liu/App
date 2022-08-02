package com.example.medicaltranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicaltranslator.databinding.SideMenuSavedBinding;

public class SideMenuSavedActivity extends SideMenuBaseActivity {

    SideMenuSavedBinding sideMenuSavedBinding;

    ImageView saved_img;
    TextView saved_name, saved_time;
    ImageButton button_favorite;
    Button button_show_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideMenuSavedBinding = SideMenuSavedBinding.inflate(getLayoutInflater());
        setContentView(sideMenuSavedBinding.getRoot());
        allocateActivityTitle("Saved Medicines");


    }
}