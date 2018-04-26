package com.example.ttt.momapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private static final String TAG = "ListDataActivity";
    private ListView mListView;
    private int position;
    private String value = "";
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private List<String> array;
    private Boolean selected = false;
    private String selectedFromList;
    protected ArrayAdapter adapter;
    protected DatabaseHelper helper;
    protected Item selectedItem;
    protected int id;
    private String name;
    private int itemID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        mListView = (ListView) findViewById(R.id.items);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


      /*  Item t1 = new Item("Peach", 2, "Basement", 5.00, "Food", "5/13/18", "");
        Item t2 = new Item("Apple", 2, "Kitchen", 2.00, "Food", "5/5/18", "");
        Item t3 = new Item("Donkey", 2, "Basement", 5.00, "Food", "5/13/18", "");
        Item t4 = new Item("Cow", 2, "Kitchen", 2.00, "Food", "5/5/18", "");
        itemList.add(t1);
        itemList.add(t2);
        itemList.add(t3);
        itemList.add(t4);*/

        helper = new DatabaseHelper(this);       /* helper.insertItem(t1);
        helper.insertItem(t2);
        helper.insertItem(t3);
        helper.insertItem(t4);*/

        EditText et1 = (EditText)findViewById(R.id.search);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, helper.getNames());
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setTextFilterEnabled(true);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                selected = true;
                selectedFromList =(mListView.getItemAtPosition(position).toString());
//                selectedItem = (Item)mListView.getItemAtPosition(position);
//                itemID = helper.getItemID(name); //get id for string name


//                data.moveToNext();
//                itemID = data.getInt(7);
//                if (itemID > -1){
//                    Log.d(TAG, "The ID is: " + itemID);
//                } else {
//                    Toast.makeText(MainActivity.this, "No ID associated with this name", Toast.LENGTH_SHORT).show();
//                }


            }
        });
        id = helper.getID(selectedFromList);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.adapter.getFilter().filter(charSequence);

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(MainActivity.this);

    }


    public void onClick(View v) {
        Button addItem = (Button) findViewById(R.id.add);
        Button editItem = (Button) findViewById(R.id.edit);
        Button deleteItem = (Button)findViewById(R.id.delete);

        if (v == editItem) {
            Intent i = new Intent(MainActivity.this, EditInventory.class);
            i.putExtra("Name", selectedFromList);
            startActivity(i);
        }


        if (v == addItem) {
            if (v.getId() == R.id.add) {
                Intent i = new Intent(MainActivity.this, InventoryAdd.class);
                startActivity(i);
            }
        }

        if(v == deleteItem){
        if(v.getId() == R.id.delete) {
//                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();

//                helper.deleteItem(id);
            Integer deletedRows =  helper.deleteName(selectedFromList);
            if(deletedRows>0)
                Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();

//               adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, helper.getNames());
//                mListView.setAdapter(adapter);
//               adapter.remove(adapter.getItem(position));
            finish();
            startActivity(getIntent());            }

    }

}

    //  public void onClick(View v){
    //    Button addItem = (Button)findViewById(R.id.add);
    //  Button editItem = (Button)findViewById(R.id.edit);
//        if(v == addItem){
    // Intent i = new Intent(MainActivity.this, Main2Activity.class);

    // i.putExtra("index",position);
    // startActivity(i);

    //        }
//        if(v == editItem){
//            Intent i = new Intent(MainActivity.this, Main2Activity.class);
//            startActivity(i);
//        }
    //}
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
            mAuth.signOut();
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

