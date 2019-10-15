package com.example.kaspe.architectureexample;

import java.util.List;
import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDao noteDao;
    private static NoteRepository instance;

    private NoteRepository(){
        noteDao = NoteDao.getInstance();
    }

    public static NoteRepository getInstance(){
        if(instance == null)
            instance = new NoteRepository();

        return instance;
    }

    public LiveData<List<Note>> getAllNotes(){
        return noteDao.getAllNotes();
    }

    public void insert(Note note) {
        noteDao.insert(note);
    }

    public void deleteAllNotes(){
        noteDao.deleteAllNotes();
    }
}
