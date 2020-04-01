package com.example.lab06;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PersonsActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> fakeNames;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_persons);

        listView = findViewById(R.id.personName);

        ArrayList<String> i = (ArrayList) this.getIntent().getExtras().get("Persons");

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, i);

        listView.setAdapter(adapter);


    }
}
