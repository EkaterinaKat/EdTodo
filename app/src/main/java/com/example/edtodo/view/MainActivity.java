package com.example.edtodo.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edtodo.R;
import com.example.edtodo.db.NoteDatabase;
import com.example.edtodo.logic.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private RecyclerView noteRecycleView;
    private FloatingActionButton addNoteButton;
    private NoteAdapter noteAdapter;
    private NoteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = NoteDatabase.getInstance(getApplication());

        initViews();

        noteAdapter = new NoteAdapter();
        noteAdapter.setOnNoteClickListener(note -> {
        });
        noteRecycleView.setAdapter(noteAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(
                    @NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Note note = noteAdapter.getNotes().get(viewHolder.getAdapterPosition());

                new Thread(() -> {
                    database.noteDao().remove(note.getId());
                    handler.post(() -> showNotes());

                }).start();

            }
        });
        itemTouchHelper.attachToRecyclerView(noteRecycleView);

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
        new Thread(() -> {
            List<Note> notes = database.noteDao().getNotes();
            handler.post(() -> noteAdapter.setNotes(notes));
        }).start();
    }
}