package com.example.medicaltranslator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class TheAdapterForHelp extends BaseExpandableListAdapter {

    Context context;
    List<String> list_group;
    HashMap<String, List<String>> list_items;

    public TheAdapterForHelp(Context context, List<String> list_group, HashMap<String, List<String>> list_items)
    {
        this.context = context;
        this.list_group = list_group;
        this.list_items = list_items;
    }

    @Override
    public int getGroupCount() {
        return list_group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.list_items.get(this.list_group.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.list_group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.list_items.get(this.list_group.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewParentGroup) {
        String parent_group = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.help_list_group,null);
        }
        TextView textView = view.findViewById(R.id.help_list_parent);
        textView.setText(parent_group);

        ImageView imageView = (ImageView) view.findViewById(R.id.help_list_parent_arrow);
        if (isExpanded == true) {
            imageView.setRotation(180);
        }
        else if (isExpanded == false){
            imageView.setRotation(0);
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewChildGroup) {
        String child_group = (String) getChild(groupPosition,childPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.help_list_item,null);
        }
        TextView textView = view.findViewById(R.id.help_list_child);
        textView.setText(child_group);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
