package com.example.edtodo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.edtodo.db.NoteDatabase;
import com.example.edtodo.logic.Note;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private final NoteDatabase database;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = NoteDatabase.getInstance(application);
    }

    public void remove(Note note) {
        new Thread(() -> database.noteDao().remove(note.getId())).start();
    }

    public LiveData<List<Note>> getNotes() {
        return database.noteDao().getNotes();
    }
}
