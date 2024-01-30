package com.example.edtodo.logic;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private final int id;
    private final String title;
    private final Priority priority;

    public Note(int id, String title, Priority priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
    }

    @Ignore
    public Note(String title, Priority priority) {
        this(0, title, priority);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }
}
