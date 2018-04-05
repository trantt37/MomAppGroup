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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String name = getIntent().getStringExtra("Name");
//        String quantity = getIntent().getStringExtra("Quantity");
//        String location = getIntent().getStringExtra("Location");
//        String price = getIntent().getStringExtra("Price");
//        String expDate = getIntent().getStringExtra("Expiration");
//        String misc = getIntent().getStringExtra("Misc");
        TextView tv = (TextView)findViewById(R.id.name_editText);
        tv.setText(name);

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
            EditText name = (EditText) findViewById(R.id.name_editText);
            EditText quantity = (EditText) findViewById(R.id.quantity_editText);
            EditText location = (EditText) findViewById(R.id.location_editText);
            EditText price = (EditText) findViewById(R.id.price_editText);
            Spinner category = (Spinner) findViewById(R.id.categoryDropdown);
            EditText expDate = (EditText) findViewById(R.id.expirationDate_editText);
            EditText misc = (EditText) findViewById(R.id.miscNotes_editText);

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
                Item item = new Item(nameStr, quantityNum, locationStr, priceNum , expStr, miscStr, catStr);
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

}