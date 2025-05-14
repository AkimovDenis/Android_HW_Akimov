package com.example.hwlesson6

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment

class NoteDetailFragment(private val note: Note) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<EditText>(R.id.editTitle)
        val desc = view.findViewById<EditText>(R.id.editDescription)
        val textDate = view.findViewById<TextView>(R.id.textDate)
        val button = view.findViewById<Button>(R.id.buttonPickDate)

        title.setText(note.title)
        desc.setText(note.description)
        textDate.text = note.date

        button.setOnClickListener {
            val calendar = Calendar.getInstance()
            DatePickerDialog(requireContext(), { _, y, m, d ->
                textDate.text = "%02d.%02d.%d".format(d, m + 1, y)
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }
    }
}
