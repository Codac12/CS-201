package com.codac.admin.familyhistoryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import model.event;
import model.person;

/**
 * Created by Admin on 4/8/17.
 */

public class peopleAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<String> headerArray;
    private HashMap<String, ArrayList<person>> childArray;
    private LayoutInflater infalInflater;

    public peopleAdapter(Context context, ArrayList<String> headerArray, HashMap<String, ArrayList<person>> childArray) {
        this.context = context;
        this.headerArray = headerArray;
        this.childArray = childArray;
        infalInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return this.headerArray.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(childArray.size() == 0)
            return 1;
        return this.childArray.get(this.headerArray.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.headerArray.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(childArray.size() == 0)
            return null;
        return this.childArray.get(this.headerArray.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        if(childArray.size() == 0)
            return 1;
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {

            convertView = infalInflater.inflate(R.layout.list_parent,
                    null);
        }

        TextView textViewHeader = (TextView) convertView
                .findViewById(R.id.list_header);
        textViewHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText;
        person prsn = (person) getChild(groupPosition, childPosition);
        if(prsn != null)
            childText = prsn.toString();
        else
            childText = "";

        if (convertView == null) {

            convertView = infalInflater.inflate(R.layout.list_child, null);
        }

        TextView textViewChild = (TextView) convertView
                .findViewById(R.id.textViewChild);

        textViewChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
