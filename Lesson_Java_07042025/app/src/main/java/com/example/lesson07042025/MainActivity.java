package com.example.lesson07042025;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

// MainActivity.java
public class MainActivity extends AppCompatActivity implements TaskListFragment.TaskListListener, AddTaskFragment.AddTaskListener, TaskDetailFragment.TaskDetailListener {
    private ArrayList<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTaskList();
    }

    private void showTaskList() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, TaskListFragment.newInstance(taskList))
                .commit();
    }

    @Override
    public void onAddTaskClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new AddTaskFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onTaskSelected(int position) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, TaskDetailFragment.newInstance(taskList.get(position), position))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onTaskAdded(Task task) {
        taskList.add(task);
        getSupportFragmentManager().popBackStack();
        showTaskList();
    }

    @Override
    public void onTaskDeleted(int position) {
        taskList.remove(position);
        getSupportFragmentManager().popBackStack();
        showTaskList();
    }
}