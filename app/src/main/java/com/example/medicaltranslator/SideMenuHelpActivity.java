package com.example.medicaltranslator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.medicaltranslator.databinding.SideMenuHelpBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SideMenuHelpActivity extends SideMenuBaseActivity {

    SideMenuHelpBinding sideMenuHelpBinding;

    ExpandableListView expandableListView;
    List<String> list_group;
    HashMap<String,List<String>> list_items;
    TheAdapterForHelp adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sideMenuHelpBinding = SideMenuHelpBinding.inflate(getLayoutInflater());
        setContentView(sideMenuHelpBinding.getRoot());
        allocateActivityTitle("Help");

        expandableListView = findViewById(R.id.expandable_listView);
        list_group = new ArrayList<>();
        list_items = new HashMap<>();
        adapter = new TheAdapterForHelp(this,list_group,list_items);
        expandableListView.setAdapter(adapter);
        initialListData();
    }

    //list the questions that can be expandable
    private void initialListData() {
        list_group.add(getString(R.string.questionA_1));
        list_group.add(getString(R.string.questionA_2));
        list_group.add(getString(R.string.questionA_3));
        list_group.add(getString(R.string.questionA_4));
        list_group.add(getString(R.string.questionA_5));

        //show answers of questions
        String[] array; //hold answers
        List<String> list_q1 = new ArrayList<>(); //set the answer to the corresponding question
        array = getResources().getStringArray(R.array.questionA_1);
        for (String question: array) {
            list_q1.add(question);
        }
        List<String> list_q2 = new ArrayList<>();
        array = getResources().getStringArray(R.array.questionA_2);
        for (String question: array) {
            list_q2.add(question);
        }
        List<String> list_q3 = new ArrayList<>();
        array = getResources().getStringArray(R.array.questionA_3);
        for (String question: array) {
            list_q3.add(question);
        }
        List<String> list_q4 = new ArrayList<>();
        array = getResources().getStringArray(R.array.questionA_4);
        for (String question: array) {
            list_q4.add(question);
        }
        List<String> list_q5 = new ArrayList<>();
        array = getResources().getStringArray(R.array.questionA_5);
        for (String question: array) {
            list_q5.add(question);
        }

        //add items into list
        list_items.put(list_group.get(0),list_q1);
        list_items.put(list_group.get(1),list_q2);
        list_items.put(list_group.get(2),list_q3);
        list_items.put(list_group.get(3),list_q4);
        list_items.put(list_group.get(4),list_q5);
        adapter.notifyDataSetChanged();
    }
}