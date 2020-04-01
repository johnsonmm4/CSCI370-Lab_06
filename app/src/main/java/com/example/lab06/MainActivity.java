package com.example.lab06;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button viewNames, submitName;
    EditText newName;
    List<String> nameList;
    final String DATABASE_NAME = "myDatabase";
    LabDatabase labDatabase;
    asyncOne insertTask;
    public static final int REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewNames = findViewById(R.id.viewNames);
        submitName = findViewById(R.id.submitNewName);
        newName =findViewById(R.id.newName);

        final Context context = getApplicationContext();
        final Intent displayIntent = new Intent(this, PersonsActivity.class);

        labDatabase  = Room.databaseBuilder(context, LabDatabase.class, DATABASE_NAME).build();

        viewNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "View names works!", Toast.LENGTH_SHORT ).show();
                ViewPeopleAsyc viewNamesAsync = new ViewPeopleAsyc(labDatabase, context);
                viewNamesAsync.execute(null, null);

                startActivity(displayIntent);
            }
        });

        submitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Submit name works!", Toast.LENGTH_SHORT ).show();
                String name = newName.getText().toString();
                Person newPerson = new Person(name);
                InsertPersonAsync insertAsync = new InsertPersonAsync(labDatabase, newPerson);
                insertAsync.execute(null,null);
                //insertAsync.doInBackground(null);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1) {
            Bundle extras = data.getExtras();
            nameList = extras.getStringArrayList("Persons");

        }
    }

    public class ViewPeopleAsyc extends AsyncTask {
        LabDatabase database;
        Person person;
        Context context;
        List<Person> persons;

        public ViewPeopleAsyc(LabDatabase database, Context context){
            this.database = database;
            this.context = context;


        }

        @Override
        protected Object doInBackground(Object[] objects) {
            persons = database.personDao().getAllPersons();
            ArrayList<String> personNames = new ArrayList<>();
            for(Person p: persons) {
                personNames.add(p.getName());
            }

            Intent i = new Intent(context, PersonsActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("Persons", personNames);
            context.startActivity(i);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
        }
    }

}
