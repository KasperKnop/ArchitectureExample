package com.example.kaspe.architectureexample;

import androidx.lifecycle.MutableLiveData;
import java.util.Timer;
import java.util.TimerTask;

public class NoteWebClient {

    private MutableLiveData<Note> note;
    private static NoteWebClient instance;

    public static NoteWebClient getInstance() {
        if (instance == null) {
            instance = new NoteWebClient();
        }
        return instance;
    }

    private NoteWebClient() {
        note = new MutableLiveData<>();
        note.setValue(new Note("Note from the internet!"));
    }

    public void getNote(final OnWebNoteReceivedListener listener) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                listener.onWebNoteReceived(note.getValue());
            }
        }, 5000);
    }
}
