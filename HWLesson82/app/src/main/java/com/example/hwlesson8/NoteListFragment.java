package com.example.hwlesson8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NoteListFragment extends Fragment {

    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private ArrayList<Note> noteList;
    private NoteListListener listener;

    public interface NoteListListener {
        void onNoteSelected(int position);

        void onEditNoteClicked(Note note, int position);
    }

    public static NoteListFragment newInstance(ArrayList<Note> notes) {
        NoteListFragment fragment = new NoteListFragment();
        Bundle args = new Bundle();
        args.putSerializable("notes", notes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_notes);
        FloatingActionButton fab = view.findViewById(R.id.fab_add_note);

        if (getArguments() != null) {
            noteList = (ArrayList<Note>) getArguments().getSerializable("notes");
        }

        adapter = new NoteAdapter(noteList, position -> {
            Note note = noteList.get(position);
            listener.onEditNoteClicked(note, position);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
           listener.onEditNoteClicked(null, -1);
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof NoteListListener) {
            listener = (NoteListListener) context;
        }
    }
}
