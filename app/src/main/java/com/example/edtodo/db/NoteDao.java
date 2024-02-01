package com.example.edtodo.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.edtodo.logic.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    Single<List<Note>> getNotes();

    @Insert
    Completable add(Note note);

    @Query("DELETE FROM note WHERE id = :id")
    Completable remove(int id);
}
