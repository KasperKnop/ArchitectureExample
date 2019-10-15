package com.example.kaspe.architectureexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

//Database placeholder - Next class we will learn how to implement persistence using the Room library.
public class NoteDao {

    private MutableLiveData<List<Note>> allNotes;
    private static NoteDao instance;

    private NoteDao() {
        allNotes = new MutableLiveData<>();
        List<Note> newList = new ArrayList<>();
        allNotes.setValue(newList);
    }

    public static NoteDao getInstance(){
        if(instance == null) {
            instance = new NoteDao();
        }
        return instance;
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        List<Note> currentNotes = allNotes.getValue();
        currentNotes.add(note);
        allNotes.postValue(currentNotes);
    }

    public void deleteAllNotes() {
        List<Note> newList = new ArrayList<>();
        allNotes.setValue(newList);
    }
}
