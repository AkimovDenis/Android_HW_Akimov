package com.example.lesson07042025;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

// AddTaskFragment.java
public class AddTaskFragment extends Fragment {
    private EditText titleInput, descInput;
    private AddTaskListener listener;

    public interface AddTaskListener {
        void onTaskAdded(Task task);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        titleInput = view.findViewById(R.id.title_input);
        descInput = view.findViewById(R.id.desc_input);
        Button saveBtn = view.findViewById(R.id.save_button);

        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String desc = descInput.getText().toString();
            listener.onTaskAdded(new Task(title, desc));
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddTaskListener) {
            listener = (AddTaskListener) context;
        }
    }
}