package com.example.ttt.momapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private int position;
    private String value = "";
    private ArrayList<Item> itemList = new ArrayList<Item>();
    private List<String> array;
    private Boolean selected = false;
    private String selectedFromList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.items);

// 1
        Item t1 = new Item("Peach",2, "Basement", 5.00, "Food", "5/13/18","");
        Item t2 = new Item("Apple",2, "Kitchen", 2.00, "Food", "5/5/18","");
        Item t3 = new Item("Donkey",2, "Basement", 5.00, "Food", "5/13/18","");
        Item t4 = new Item("Cow",2, "Kitchen", 2.00, "Food", "5/5/18","");
        itemList.add(t1);
        itemList.add(t2);
        itemList.add(t3);
        itemList.add(t4);

        DatabaseHelper helper = new DatabaseHelper(this);
        helper.insertItem(t1);
        helper.insertItem(t2);
        helper.insertItem(t3);
        helper.insertItem(t4);



        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, helper.getNames());
        mListView.setAdapter(adapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                selected = true;
                selectedFromList =(mListView.getItemAtPosition(position).toString());
            }
        });


    }

    public void onClick(View v) {
        Button addItem = (Button) findViewById(R.id.add);
        Button editItem = (Button) findViewById(R.id.edit);
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
}

