package com.example.kaspe.architectureexample;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.core.os.HandlerCompat;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private static NoteRepository instance;
    private final NoteDao noteDao;
    private final LiveData<List<Note>> allNotes;
    ExecutorService executorService;
    Handler mainThreadHandler;

    private NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
        executorService = Executors.newFixedThreadPool(2);
        mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    public static synchronized NoteRepository getInstance(Application application) {
        if (instance == null)
            instance = new NoteRepository(application);

        return instance;
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        executorService.execute(() -> {
            noteDao.insert(note);
            mainThreadHandler.post(() -> {/*You can execute code on the main thread in here*/});
        });
    }

    public void deleteAllNotes() {
        executorService.execute(noteDao::deleteAllNotes);
    }
}
