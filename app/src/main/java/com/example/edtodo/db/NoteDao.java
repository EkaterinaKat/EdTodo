package com.example.edtodo.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.edtodo.logic.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getNotes();

    @Insert
    Completable add(Note note);

    @Query("DELETE FROM note WHERE id = :id")
    void remove(int id);
}
