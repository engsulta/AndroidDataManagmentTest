package com.example.sulta.datamanagment.com.example.sulta.beans;


/**
 * Created by sulta on 8/13/2017.
 */

import java.io.Serializable;

/**
 * Created by sulta on 6/21/2017.


 /**
 * Created by Mohamed Hamdy on 2/23/2016.
 */
public class Note implements Serializable
{
    private int id;
    private String note,time,alarm;

    public Note(int id, String note, String time, String alarm) {
        this.id = id;
        this.alarm = alarm;
        this.note = note;
        this.time = time;
    }

   /* public Note(String note, String time, String alarm) {
        this.alarm = alarm;
        this.note = note;
        this.time = time;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlarm() {
        return this.alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return note;
    }
}

