package com.example.kaspe.architectureexample;

import java.util.List;
import androidx.lifecycle.LiveData;

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(){
        noteDao = new NoteDao();
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
