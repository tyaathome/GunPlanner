package com.example.trungie.gunplanner.object;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.trungie.gunplanner.R;

import java.util.List;


public class BackLog extends AppCompatActivity {

    private NotesDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_log);

        datasource = new NotesDataSource(this);
        List<NoteItem> notes = datasource.findAll();
        NoteItem note = notes.get(0);
        note.setText("Updated!");
        datasource.update(note);

        notes = datasource.findAll();
        note = notes.get(0);

        Log.i("NOTES",note.getText() + ": " + note.getText());

    }

}
