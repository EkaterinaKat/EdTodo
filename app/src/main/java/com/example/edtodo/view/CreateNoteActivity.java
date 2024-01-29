package com.example.edtodo.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.edtodo.R;
import com.example.edtodo.db.Database;
import com.example.edtodo.logic.Note;
import com.example.edtodo.logic.Priority;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText noteEditText;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private Button saveNoteButton;

    public static Intent newIntent(Context context) {
        return new Intent(context, CreateNoteActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        initViews();
        saveNoteButton.setOnClickListener(view -> saveButtonListener());
    }

    private void initViews() {
        noteEditText = findViewById(R.id.noteEditText);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        saveNoteButton = findViewById(R.id.saveNoteButton);
    }

    private void saveButtonListener() {
        Note note = new Note(
                Database.getInstance().getNotes().size(),
                noteEditText.getText().toString().trim(),
                getSelectedPriority());
        Database.getInstance().add(note);

        finish();
    }

    private Priority getSelectedPriority() {
        Priority priority;
        if (radioButtonLow.isChecked()) {
            priority = Priority.LOW;
        } else if (radioButtonMedium.isChecked()) {
            priority = Priority.MEDIUM;
        } else {
            priority = Priority.HIGH;
        }
        return priority;
    }
}