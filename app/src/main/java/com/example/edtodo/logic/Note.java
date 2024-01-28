package com.example.edtodo.logic;

public class Note {
    private final int id;
    private final String title;
    private final Priority priority;

    public Note(int id, String title, Priority priority) {
        this.id = id;
        this.title = title;
        this.priority = priority;
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
