package com.example.hwlesson8;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

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
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, EditNoteFragment.newInstance(noteList.get(position), position))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNoteSaved(Note note, int position) {
        if (position >= 0) {
            noteList.set(position, note);
        } else {
            noteList.add(note);
        }
        getSupportFragmentManager().popBackStack();
        showNoteList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, EditNoteFragment.newInstance(null, -1))
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEditNoteClicked(Note note, int position) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, EditNoteFragment.newInstance(note, position))
                .addToBackStack(null)
                .commit();
    }

}