package com.example.kaspe.architectureexample;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if (!notes.isEmpty()) {
                    textView.setText("");
                    for (Note n : notes) {
                        textView.append(n.getTitle() + "\n");
                    }
                } else {
                    textView.setText("Empty");
                }
            }
        });
    }

    public void saveNote(View v) {
        noteViewModel.insert(new Note(editText.getText().toString(),"description",1));
    }

    public void deleteAllNotes(View v) {
        noteViewModel.deleteAllNotes();
    }
}
