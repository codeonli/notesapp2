package com.example.notesapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.notesapp.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NotesDataSource {
    private SQLiteDatabase database;
    private NotesDatabaseHelper dbHelper;

    public NotesDataSource(Context context) {
        dbHelper = new NotesDatabaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createNote(Note note) {
        ContentValues values = new ContentValues();
        values.put(NotesDatabaseHelper.COLUMN_NOTE_TEXT, note.getNoteText());
        values.put(NotesDatabaseHelper.COLUMN_DATE, note.getDate());

        return database.insert(NotesDatabaseHelper.TABLE_NOTES, null, values);
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        Cursor cursor = database.query(NotesDatabaseHelper.TABLE_NOTES, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Note note = cursorToNote(cursor);
            notes.add(note);
            cursor.moveToNext();
        }

        cursor.close();
        return notes;
    }

    private Note cursorToNote(Cursor cursor) {
        Note note = new Note();
        int idIndex = cursor.getColumnIndex(NotesDatabaseHelper.COLUMN_ID);
        int noteIndex = cursor.getColumnIndex(NotesDatabaseHelper.COLUMN_NOTE_TEXT);
        int dateIndex = cursor.getColumnIndex(NotesDatabaseHelper.COLUMN_DATE);
        if (idIndex >= 0 && noteIndex >= 0 && dateIndex >= 0) {
            note.setId(cursor.getLong(idIndex));
            note.setNoteText(cursor.getString(noteIndex));
            note.setDate(cursor.getString(dateIndex));
        }
        return note;
    }
}

