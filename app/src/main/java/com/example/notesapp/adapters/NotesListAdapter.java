package com.example.notesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.databinding.NotesItemBinding;
import com.example.notesapp.model.Note;

import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {

    List<Note> notes;

    public NotesListAdapter(List<Note> notesList) {
        this.notes = notesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        NotesItemBinding binding = NotesItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note note = notes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        NotesItemBinding layoutBinding;

        public ViewHolder(NotesItemBinding binding) {
            super(binding.getRoot());
            layoutBinding = binding;
        }

        public void bind(Note note) {
            layoutBinding.noteText.setText(note.getNoteText());
            layoutBinding.dateText.setText(note.getDate());
        }
    }
}
