package com.codac.admin.familyhistoryapp;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class searchActivity extends AppCompatActivity {

    MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.up_arrow:
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        item = (MenuItem) menu.findItem(R.id.up_arrow);
        Drawable upIcon = new IconDrawable(this, Iconify.IconValue.fa_angle_double_up).sizeDp(40);
        item.setIcon(upIcon);

//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        ComponentName componentName = new ComponentName(this, searchResultsActivity.class);
//
//        MenuItem searchItem = (MenuItem) menu.findItem(R.id.search_query);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
//
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // Change do filter for search!
//                return false;
//            }
//        });

        return true;
    }

}
