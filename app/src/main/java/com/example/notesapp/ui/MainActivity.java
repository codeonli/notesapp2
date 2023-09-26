package com.example.notesapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;

import com.example.notesapp.adapters.NotesListAdapter;
import com.example.notesapp.databinding.ActivityMainBinding;
import com.example.notesapp.db.NotesDataSource;
import com.example.notesapp.model.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private NotesDataSource dataSource;
    private NotesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        dataSource = new NotesDataSource(this);

        try {
            dataSource.open();

            List<Note> allNotes = dataSource.getAllNotes();
            initializRecyclerView(allNotes);

        } catch (SQLException e) {
            // Handle database-related exceptions
            e.printStackTrace();
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }

    private void initializRecyclerView(List<Note> notes) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.notesList.setLayoutManager(layoutManager);
        adapter = new NotesListAdapter(notes);
        binding.notesList.setAdapter(adapter);
    }
}