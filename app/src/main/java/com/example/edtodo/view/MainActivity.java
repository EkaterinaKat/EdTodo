package com.example.edtodo.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.edtodo.R;
import com.example.edtodo.db.Database;
import com.example.edtodo.logic.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private LinearLayout noteBox;
    private FloatingActionButton addNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        addNoteButton.setOnClickListener(view -> {
            Intent intent = CreateNoteActivity.newIntent(this);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showNotes();
    }

    private void initViews() {
        noteBox = findViewById(R.id.noteBox);
        addNoteButton = findViewById(R.id.addNoteButton);
    }

    private void showNotes() {
        noteBox.removeAllViews();
        for (Note note : Database.getInstance().getNotes()) {
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