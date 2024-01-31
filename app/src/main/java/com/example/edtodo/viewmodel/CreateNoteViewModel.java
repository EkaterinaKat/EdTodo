package com.example.edtodo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.edtodo.db.NoteDatabase;
import com.example.edtodo.logic.Note;

public class CreateNoteViewModel extends AndroidViewModel {
    private final NoteDatabase database;
    private final MutableLiveData<Boolean> shouldCloseScreen = new MutableLiveData<>();

    public CreateNoteViewModel(@NonNull Application application) {
        super(application);
        database = NoteDatabase.getInstance(application);
    }

    public void saveNote(Note note) {
        new Thread(() -> {
            database.noteDao().add(note);
            shouldCloseScreen.postValue(true);
        }).start();
    }

    public MutableLiveData<Boolean> getShouldCloseScreen() {
        return shouldCloseScreen;
    }
}
