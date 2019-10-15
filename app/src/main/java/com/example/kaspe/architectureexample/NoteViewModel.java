package com.example.kaspe.architectureexample;

import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NoteViewModel extends ViewModel implements OnWebNoteReceivedListener {

    private NoteRepository repository;
    private MutableLiveData<Boolean> isLoading;

    public NoteViewModel() {
        repository = NoteRepository.getInstance();
        isLoading = new MutableLiveData<>();
        isLoading.setValue(false);
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

    public void addNoteFromWeb() {
        isLoading.setValue(true);
        repository.addNoteFromWeb(this);
    }

    public LiveData<Boolean> getIsLoading(){
        return isLoading;
    }

    @Override
    public void onWebNoteReceived(Note note) {
        repository.insert(note);
        isLoading.postValue(false);
    }
}
