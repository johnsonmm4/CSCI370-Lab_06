package com.example.lab06;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class asyncOne extends AsyncTask {
    LabDatabase database;
    Person person;
    Context context;
    List<Person> persons;
    public static int response_code = 1;

    public asyncOne(LabDatabase database, Context context){
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
        i.putExtra("Persons", personNames);
        context.startActivity(i);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
