package com.example.kaspe.architectureexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    private MutableLiveData<List<Note>> allNotes;

    public NoteDao() {
        allNotes = new MutableLiveData<>();
        List<Note> newList = new ArrayList<>();
        allNotes.setValue(newList);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        List<Note> currentNotes = allNotes.getValue();
        currentNotes.add(note);
        allNotes.setValue(currentNotes);
    }

    public void deleteAllNotes() {
        List<Note> newList = new ArrayList<>();
        allNotes.setValue(newList);
    }
}
