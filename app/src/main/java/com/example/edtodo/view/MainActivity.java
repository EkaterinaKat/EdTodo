package com.example.edtodo.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edtodo.R;
import com.example.edtodo.db.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private RecyclerView noteRecycleView;
    private FloatingActionButton addNoteButton;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        noteAdapter = new NoteAdapter();
        noteRecycleView.setAdapter(noteAdapter);

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
        noteRecycleView = findViewById(R.id.noteRecycleView);
        addNoteButton = findViewById(R.id.addNoteButton);
    }

    private void showNotes() {
        noteAdapter.setNotes(Database.getInstance().getNotes());
    }
}