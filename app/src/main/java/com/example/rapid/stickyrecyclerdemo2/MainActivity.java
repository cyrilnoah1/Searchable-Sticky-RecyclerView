package com.example.rapid.stickyrecyclerdemo2;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,
        RecyclerAdapter.ResetAdapter {
    final String TAG = "MainActivity";

    RecyclerAdapter mRecyclerAdapter;
    RecyclerView mRecyclerView;
    List<Contact> mRecyclerData;

    RecyclerAdapter.ResetAdapter resetAdapter;

    Toolbar mToolbar; // For providing search option.
    SearchView mSearchView; // SearchView for the Toolbar.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetAdapter = this;
        // Setting up Toolbar
        setupToolbar();
        // Setting up RecyclerView
        setupRecyclerView();
    }

    /**
     * To setup Toolbar
     */
    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main_actv);
        setSupportActionBar(mToolbar);
    }

    /**
     * Setting up RecyclerView.
     */
    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_main_actv);
        mRecyclerData = new ArrayList<>();
        // Setting the Recycler data.
        recyclerListData();

        mRecyclerAdapter = new RecyclerAdapter(mRecyclerData, resetAdapter, this);
        mRecyclerView.setLayoutManager(new StickyHeaderLayoutManager());

        mRecyclerView.setAdapter(mRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.search:
                mSearchView = (SearchView) MenuItemCompat.getActionView(item);
                mSearchView.setIconifiedByDefault(false);
                mSearchView.setOnQueryTextListener(this);
                mSearchView.setQueryHint("Contact Names");
        }
        return true;
    }

    /**
     * Method to populate dummy data into the Sticky RecyclerView.
     */
    public void recyclerListData() {
        try {
            mRecyclerData.add(new Contact("Ayeshu", "1232354234", "c@n.com", "invited", true));
            mRecyclerData.add(new Contact("Bhanu", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Bhavesh", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Cyril", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Chaitanya", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Chaitu", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Chintu", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Dhoni", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Darsh", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Druv", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Eithen", "1232354234", "c@n.com", "invited", false));
            mRecyclerData.add(new Contact("Elisa", "1232354234", "c@n.com", "invited", false));

        } catch (NullPointerException nPE) {
            nPE.printStackTrace();
        } catch (Resources.NotFoundException nFE) {
            nFE.printStackTrace();
        }
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.contactFilter(newText, mRecyclerData);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void resetAdapter(List<Contact> contacts) {
        Log.v(TAG, "Contacts: " + contacts.size());
        mRecyclerAdapter = new RecyclerAdapter(contacts, resetAdapter, this);
        mRecyclerView.setAdapter(mRecyclerAdapter);
//        mRecyclerAdapter.notifyAllSectionsDataSetChanged();
    }
}
