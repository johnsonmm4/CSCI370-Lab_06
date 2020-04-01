package com.example.lab06;

import android.os.AsyncTask;

public class InsertPersonAsync extends AsyncTask {
    LabDatabase database;
    Person person;

    public InsertPersonAsync(LabDatabase database, Person person){
        this.database = database;
        this.person= person;

    }

    @Override
    protected Object doInBackground(Object[] objects) {
        database.personDao().insertPerson(person);
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}
