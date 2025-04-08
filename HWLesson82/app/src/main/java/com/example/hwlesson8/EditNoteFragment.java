package com.example.hwlesson8;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class EditNoteFragment extends Fragment {
    private EditText titleInput, descInput;
    private EditNoteListener listener;
    private int notePosition = -1;

    public interface EditNoteListener {
        void onNoteSaved(Note note, int position);
    }

    public static EditNoteFragment newInstance(Note note, int position) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        if (note != null) {
            args.putString("title", note.getTitle());
            args.putString("desc", note.getDescription());
        }
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);
        titleInput = view.findViewById(R.id.title_input);
        descInput = view.findViewById(R.id.desc_input);
        Button saveBtn = view.findViewById(R.id.save_button);

        if (getArguments() != null) {
            titleInput.setText(getArguments().getString("title", ""));
            descInput.setText(getArguments().getString("desc", ""));
            notePosition = getArguments().getInt("position", -1);
        }

        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String desc = descInput.getText().toString();
            listener.onNoteSaved(new Note(title, desc), notePosition);
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditNoteListener) {
            listener = (EditNoteListener) context;
        }
    }
}
