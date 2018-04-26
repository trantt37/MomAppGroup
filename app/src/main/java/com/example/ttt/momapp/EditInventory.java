package com.example.ttt.momapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rohat on 4/3/2018.
 */

public class EditInventory extends AppCompatActivity {
    private String selectedName;
    private int id;
    protected DatabaseHelper helper;
    private EditText name;
    private EditText quantity;
    private EditText location;
    private EditText price;
    private Spinner category;
    private EditText expDate;
    private EditText misc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_inventory_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.name_editText);
        quantity = (EditText) findViewById(R.id.quantity_editText);
        location = (EditText) findViewById(R.id.location_editText);
        price = (EditText) findViewById(R.id.price_editText);
        category = (Spinner) findViewById(R.id.categoryDropdown);
        expDate = (EditText) findViewById(R.id.expirationDate_editText);
        misc = (EditText) findViewById(R.id.miscNotes_editText);

//        quantity.setText("5");
//        name.setText("bob");
//        price.setText("5.00");
//        location.setText("jamaica");
//        expDate.setText("05/03/14");
//        misc.setText("N/A");

        Toast.makeText(this, "set works", Toast.LENGTH_SHORT).show();
//

        selectedName = getIntent().getStringExtra("Name");
//        id = getIntent().getIntExtra("id",0);
        helper = new DatabaseHelper(this);

        name.setText(selectedName);
//            quantity.setText(helper.getItemCount(selectedName)+"");
//            location.setText(helper.getItemLocation(selectedName));
//            price.setText(helper.getItemPrice(selectedName)+"");
////            category.setText(helper.getItemCount(id));
//            expDate.setText(helper.getItemExpiration(selectedName)+"");
//            misc.setText(helper.getItemMisc(selectedName));

//        EditText tv = (EditText) findViewById(R.id.name_editText);
//        tv.setText(selectedName);
//
//        TextView tvL = (TextView)findViewById(R.id.location_editText);
//        tvL.setText((CharSequence) helper.getItemLocation(selectedName));

//        TextView tvP = (TextView)findViewById(R.id.price_editText);
//        tvP.setText((double) helper.getItemPrice(selectedName));

//        if (!helper.getItemExpiration(selectedName).equals("")) {
//            TextView tvE = (TextView) findViewById(R.id.expirationDate_editText);
//            tvE.setText((CharSequence) helper.getItemExpiration(selectedName));
//        }
//
//        if (!helper.getItemMisc(selectedName).equals("")) {
//            TextView tvM = (TextView) findViewById(R.id.miscNotes_editText);
//            tvM.setText((CharSequence) helper.getItemMisc(selectedName));
//        }
        Spinner dropdown = findViewById(R.id.categoryDropdown);
        //create a list of items for the spinner.
        final String[] items = new String[]{"Food", "Clothing", "Electronics", "Other"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_navigation_drawer, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickUpdate(View v){

        if(v.getId() == R.id.Bupdate){
            name = (EditText) findViewById(R.id.name_editText);
            quantity = (EditText) findViewById(R.id.quantity_editText);
            location = (EditText) findViewById(R.id.location_editText);
            price = (EditText) findViewById(R.id.price_editText);
            category = (Spinner) findViewById(R.id.categoryDropdown);
            expDate = (EditText) findViewById(R.id.expirationDate_editText);
            misc = (EditText) findViewById(R.id.miscNotes_editText);
//
//            quantity.setText(helper.getItemCount(selectedName));
//            location.setText(helper.getItemCount(selectedName));
//            price.setText(helper.getItemCount(selectedName));
////            category.setText(helper.getItemCount(id));
//            expDate.setText(helper.getItemCount(selectedName));
//            misc.setText(helper.getItemCount(selectedName));




            String nameStr = name.getText().toString();
            String quantityStr = quantity.getText().toString();
            String locationStr = location.getText().toString();
            String priceStr = price.getText().toString();
            String catStr = category.getSelectedItem().toString();
            String expStr = expDate.getText().toString();
            String miscStr = misc.getText().toString();



            int quantityNum = Integer.parseInt(quantityStr);
            double priceNum = Double.parseDouble(priceStr);


            if (!nameStr.isEmpty() && quantityNum != 0 && !locationStr.isEmpty() && priceNum != 0){
                Item item = new Item(nameStr, quantityNum, locationStr, priceNum , expStr, miscStr, catStr, 0);
                DatabaseHelper helper = new DatabaseHelper(this);
                helper.updateItem(item);

                Intent i = new Intent(EditInventory.this, MainActivity.class);
                startActivity(i);
            }
            else if (nameStr.isEmpty()){
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            }
            else if (quantityNum == 0){
                Toast.makeText(this, "Please enter a quantity", Toast.LENGTH_SHORT).show();
            }
            else if (locationStr.isEmpty()){
                Toast.makeText(this, "Please enter a location", Toast.LENGTH_SHORT).show();
            }
            else if (priceNum == 0){
                Toast.makeText(this, "Please enter a price", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void onClickAdd(View v){
        int numQ;
        if (v.getId() == R.id.BaddItem){
            TextView quantity = (TextView) findViewById(R.id.quantity_editText);
            if (!quantity.equals(null)){
                numQ = Integer.parseInt(quantity.getText().toString());
                numQ = numQ + 1;
                quantity.setText("" + numQ);
            }
        }
    }

    public void onClickSubtract(View v){
        int numQ = 0;
        if (v.getId() == R.id.BsubtractItem){
            TextView quantity = (TextView) findViewById(R.id.quantity_editText);
            if (!quantity.equals("") && !quantity.equals(null)){
                numQ = Integer.parseInt(quantity.getText().toString());
                numQ = numQ - 1;
            }
            quantity.setText("" + numQ);
        }
    }

}