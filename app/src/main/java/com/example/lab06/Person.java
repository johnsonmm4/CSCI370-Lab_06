package com.example.lab06;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)

    private int id;
    private String name;

    public Person(String name) {
        this.id = 0;
        this.name = name;
    }

    public Person(){
        this.id = 0;
        this.name= "Standin";
    }

    public int getId(){ return this.id; }
    public void setId(int id){ this.id = id; }

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }

}
