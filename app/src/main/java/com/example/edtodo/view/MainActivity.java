package com.example.edtodo.view;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.edtodo.R;
import com.example.edtodo.logic.Note;
import com.example.edtodo.logic.Priority;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout noteBox;
    private FloatingActionButton addNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        showNotes();
    }

    private void initViews() {
        noteBox = findViewById(R.id.noteBox);
        addNoteButton = findViewById(R.id.addNoteButton);
    }

    private void showNotes() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Note note = new Note(i, "Note " + i, Priority.getById(random.nextInt(3)));
            showNoteInBox(note);
        }
    }

    private void showNoteInBox(Note note) {
        View view = getLayoutInflater().inflate(R.layout.note_item, noteBox, false);
        TextView noteTextView = view.findViewById(R.id.noteTextView);
        noteTextView.setText(note.getTitle());
        noteTextView.setBackgroundColor(ContextCompat.getColor(this, note.getPriority().getColorResId()));
        noteBox.addView(view);
    }
}