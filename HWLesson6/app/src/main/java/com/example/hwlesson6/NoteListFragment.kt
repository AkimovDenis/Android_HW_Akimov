package com.example.hwlesson6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NoteListFragment(private val onNoteSelected: (Note) -> Unit) : Fragment() {

    private val notes = listOf(
        Note("Заметка 1", "Описание 1", "01.05.2025"),
        Note("Заметка 2", "Описание 2", "03.05.2025")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler = view.findViewById<RecyclerView>(R.id.recyclerNotes)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = NoteAdapter(notes, onNoteSelected)
    }
}
