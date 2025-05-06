package com.example.hwlesson7;

// NoteDetailFragment.java

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NoteDetailFragment extends Fragment {
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "description";
    private static final String ARG_DATE = "date";

    public static NoteDetailFragment newInstance(Note note) {
        NoteDetailFragment fragment = new NoteDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, note.getTitle());
        args.putString(ARG_DESC, note.getDescription());
        args.putString(ARG_DATE, note.getDate());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_detail, container, false);
        TextView titleView = view.findViewById(R.id.detail_title);
        TextView descView = view.findViewById(R.id.detail_description);
        TextView dateView = view.findViewById(R.id.detail_date);

        if (getArguments() != null) {
            titleView.setText(getArguments().getString(ARG_TITLE));
            descView.setText(getArguments().getString(ARG_DESC));
            dateView.setText(getArguments().getString(ARG_DATE));
        }
        return view;
    }
}
