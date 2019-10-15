package com.example.kaspe.architectureexample;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class NoteViewModel extends ViewModel {

    private NoteRepository repository;

    public NoteViewModel() {
        repository = NoteRepository.getInstance();
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
