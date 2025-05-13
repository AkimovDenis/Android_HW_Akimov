package com.example.hwlesson7;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteListFragment.NoteListListener, EditNoteFragment.EditNoteListener {
    private ArrayList<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showNoteList();
    }

    private void showNoteList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, NoteListFragment.newInstance(noteList))
                .commit();
    }

    @Override
    public void onNoteSelected(int position) {
        boolean isLandscape = getResources().getBoolean(R.bool.is_landscape);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        NoteDetailFragment detailFragment = NoteDetailFragment.newInstance(noteList.get(position));

        if (isLandscape) {
            transaction.replace(R.id.detail_container, detailFragment);
        } else {
            transaction.replace(R.id.fragment_container, detailFragment).addToBackStack(null);
        }
        transaction.commit();
    }

    @Override
    public void onAddNote() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new EditNoteFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNoteSaved(Note note) {
        noteList.add(note);
        getSupportFragmentManager().popBackStack();
        showNoteList();
    }
}