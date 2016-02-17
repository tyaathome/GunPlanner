package com.example.trungie.gunplanner.object;

import android.annotation.SuppressLint;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteItem implements Serializable{

    private String key;
    private String text;
    public String getKey(){
        return key;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getText(){
        return text;
    }
    public void setText(String text){
        this.text = text;
    }

    @SuppressLint("SimpleDateFormat")
    public static NoteItem getNew() {
        Locale locale = new Locale("en_AU");
        Locale.setDefault(locale);

        String pattern = "yyyy-MM-dd HH:mm:ss Z";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String key = formatter.format(new Date());

        NoteItem note = new NoteItem();
        note.setKey(key);
        note.setText("");
        return note;
    }
}