package com.example.hwlesson7;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class EditNoteFragment extends Fragment {
    private EditText titleInput, descInput;
    private TextView dateView;
    private EditNoteListener listener;

    public interface EditNoteListener {
        void onNoteSaved(Note note);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_note, container, false);
        titleInput = view.findViewById(R.id.title_input);
        descInput = view.findViewById(R.id.desc_input);
        dateView = view.findViewById(R.id.date_text);
        Button pickDateBtn = view.findViewById(R.id.pick_date_button);
        Button saveBtn = view.findViewById(R.id.save_button);

        pickDateBtn.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(requireContext(), (view1, year, month, dayOfMonth) -> {
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                dateView.setText(selectedDate);
            },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String desc = descInput.getText().toString();
            String date = dateView.getText().toString();
            listener.onNoteSaved(new Note(title, desc, date));
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