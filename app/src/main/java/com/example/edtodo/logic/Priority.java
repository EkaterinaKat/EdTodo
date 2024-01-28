package com.example.edtodo.logic;

import java.util.Arrays;

public enum Priority {
    LOW(0, android.R.color.holo_green_light),
    MEDIUM(1, android.R.color.holo_orange_light),
    HIGH(2, android.R.color.holo_red_light);

    private final int id;
    private final int colorResId;

    Priority(int id, int colorResId) {
        this.id = id;
        this.colorResId = colorResId;
    }

    public static Priority getById(int id) {
        return Arrays.stream(Priority.values())
                .filter(priority -> priority.getId() == id)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    public int getId() {
        return id;
    }

    public int getColorResId() {
        return colorResId;
    }
}
