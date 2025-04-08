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

// TaskListFragment.java
public class TaskListFragment extends Fragment {
    private RecyclerView recyclerView;
    private NoteAdapter adapter;
    private ArrayList<Note> taskList;
    private TaskListListener listener;

    public interface TaskListListener {
        void onAddTaskClicked();
        void onTaskSelected(int position);
    }

    public static TaskListFragment newInstance(ArrayList<Note> tasks) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putSerializable("tasks", tasks);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        if (getArguments() != null) {
            taskList = (ArrayList<Note>) getArguments().getSerializable("tasks");
        }

        adapter = new NoteAdapter(taskList, position -> listener.onTaskSelected(position));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddTaskActivity.class);
            startActivityForResult(intent, 1);
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof TaskListListener) {
            listener = (TaskListListener) context;
        }
    }
}