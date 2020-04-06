package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String text;
    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;
    String date;
    TextView btgenerated;
   // File filedir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvItems = (ListView) findViewById(R.id.lvItems);
        btgenerated = (TextView) findViewById(R.id.showtittle);
        try {
            readItems();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent incoming = getIntent();
        date = incoming.getStringExtra("date");

        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        // Setup remove listener method call
        setupListViewListener();

        Toast.makeText(MainActivity.this,"Welcome To Your List",Toast.LENGTH_SHORT).show();
    }
    // Attaches a long click listener to the listview to delete items
    private void setupListViewListener() {
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        items.remove(pos);

                        itemsAdapter.notifyDataSetChanged();
                        writeItems();

                        return true;
                    }
                });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int pos, long id) {

                Intent intent = new Intent(MainActivity.this,Work.class);

               // intent.putExtra("items",((TextView)item).getText().toString()); this is the ono that works with random key
                intent.putExtra("items",items);
                startActivity(intent);
            }
        });
    }

    public void onAddItem(View v) {
        EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
        String itemText = etNewItem.getText().toString();
        itemsAdapter.add(itemText);
        etNewItem.setText("");
        writeItems();
    }
    //so that items are stored after app closes and then are rewritten
    private void readItems() throws IOException {
        try {
            File filesDir = getFilesDir();
            File todoFile = new File(filesDir, "todo.txt");
            BufferedReader br = new BufferedReader(new FileReader(filesDir));

            text = br.readLine();
            items = new ArrayList<String>(Integer.parseInt(text));
        } catch (IOException e) {
            items = new ArrayList<String>();
        }
    }
    private void writeItems() {

        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileWriter fw = new FileWriter(filesDir);
            fw.write(text + date);

            fw.close();
            btgenerated.setText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // puedo tener el nombre pasado por shared preferences y de esa manera crear el archivo a ese nombre
    //  intent para abrir el respectivo archivo
    // Item Click Listener for the listview
}
