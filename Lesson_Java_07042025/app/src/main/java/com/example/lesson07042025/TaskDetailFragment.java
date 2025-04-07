package com.example.lesson07042025;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

// TaskDetailFragment.java
public class TaskDetailFragment extends Fragment {
    private TextView titleView, descView;
    private int taskPosition;
    private TaskDetailListener listener;

    public interface TaskDetailListener {
        void onTaskDeleted(int position);
    }

    public static TaskDetailFragment newInstance(Task task, int position) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", task.getTitle());
        args.putString("desc", task.getDescription());
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_detail, container, false);
        titleView = view.findViewById(R.id.detail_title);
        descView = view.findViewById(R.id.detail_desc);
        Button deleteBtn = view.findViewById(R.id.delete_button);

        if (getArguments() != null) {
            titleView.setText(getArguments().getString("title"));
            descView.setText(getArguments().getString("desc"));
            taskPosition = getArguments().getInt("position");
        }

        deleteBtn.setOnClickListener(v -> listener.onTaskDeleted(taskPosition));
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TaskDetailListener) {
            listener = (TaskDetailListener) context;
        }
    }
}