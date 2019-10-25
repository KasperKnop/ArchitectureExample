package com.example.kaspe.architectureexample;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;
import androidx.lifecycle.LiveData;

public class NoteRepository {

    private NoteDao noteDao;
    private static NoteRepository instance;
    private LiveData<List<Note>> allNotes;

    private NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public static synchronized NoteRepository getInstance(Application application){
        if(instance == null)
            instance = new NoteRepository(application);

        return instance;
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    public void insert(Note note) {
        new InsertNoteAsync(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesAsync(noteDao).execute();
    }


    private static class InsertNoteAsync extends AsyncTask<Note,Void,Void> {
        private NoteDao noteDao;

        private InsertNoteAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsync extends AsyncTask<Void,Void,Void> {
        private NoteDao noteDao;

        private DeleteAllNotesAsync(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
