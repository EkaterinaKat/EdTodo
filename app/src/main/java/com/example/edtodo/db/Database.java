package com.example.edtodo.db;

import com.example.edtodo.logic.Note;
import com.example.edtodo.logic.Priority;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Database {
    private static Database instance;
    private final List<Note> notes = new ArrayList<>();

    private Database() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            Note note = new Note(i, "Note " + i, Priority.getById(random.nextInt(3)));
            add(note);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void add(Note note) {
        notes.add(note);
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public void remove(int id) {
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.getId() == id) {
                notes.remove(note);
            }
        }
    }
}
