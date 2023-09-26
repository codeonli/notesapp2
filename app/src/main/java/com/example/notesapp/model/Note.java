package com.example.notesapp.model;

public class Note {
    private long id;
    private String noteText;
    private String date;

    public Note() {
        // Default constructor
    }

    public Note(String noteText, String date) {
        this.noteText = noteText;
        this.date = date;
    }

    // Getter and setter methods for id, noteText, and date
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
