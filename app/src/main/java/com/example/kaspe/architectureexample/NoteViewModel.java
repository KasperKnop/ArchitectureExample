package com.example.kaspe.architectureexample;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private final NoteRepository repository;

    public NoteViewModel(Application app) {
        super(app);
        repository = NoteRepository.getInstance(app);
    }

    public LiveData<List<Note>> getAllNotes() {
        return repository.getAllNotes();
    }

    public void insert(final Note note) {
        repository.insert(note);
    }

    public void deleteAllNotes() {
        repository.deleteAllNotes();
    }
}
