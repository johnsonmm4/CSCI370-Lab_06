package com.example.lab06;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1)
public abstract class LabDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}