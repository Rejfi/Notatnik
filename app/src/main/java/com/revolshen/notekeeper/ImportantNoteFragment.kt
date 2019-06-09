package com.revolshen.notekeeper

import android.os.Bundle
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.important_note_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*

class ImportantNoteFragment: Fragment(), FragmentInterface{

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.important_note_fragment, container, false)

    }

    override fun onResume() {
    /*
        val dbHelper = SQLDataBaseHelper(requireContext())
        val db = dbHelper.writableDatabase

        //Receive data from database
        val cursor = db.query(TableInfo.TABLE_NAME, null, "important = 1",
            null, null, null, null)

        cursor.moveToFirst()

        val importantNotes = ArrayList<Note>()
        while(!cursor.isAfterLast){
            val note = Note()
            note.title = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_TITLE))
            note.message = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_MESSAGE))
            // note.date = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_DATE))
            note.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            note.important = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_NAME_IMPORTANT))

            if(note.important == 1){
                importantNotes.add(note)
            }
            cursor.moveToNext()
        }

        //Close cursor and database
        cursor.close()
        db.close()
    */
        //Set layout and adapter for recycler view
        /*
        importantNoteRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        importantNoteRecyclerView.adapter = CardViewAdapter(importantNotes)
        */
        val importantNotes = receiveDataFromDataBase()
        setDataRecyclerView(importantNotes)

        super.onResume()
    }

    override fun setDataRecyclerView(notes: ArrayList<Note>) {
        importantNoteRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        importantNoteRecyclerView.adapter = CardViewAdapter(notes)
        super.setDataRecyclerView(notes)
    }

    override fun receiveDataFromDataBase(): ArrayList<Note>{

        val dbHelper = SQLDataBaseHelper(requireContext())
        val db = dbHelper.writableDatabase

        //Receive data from database
        val cursor = db.query(TableInfo.TABLE_NAME, null, "important = 1",
            null, null, null, null)

        cursor.moveToFirst()

        val importantNotes = ArrayList<Note>()
        while(!cursor.isAfterLast){
            val note = Note()
            note.title = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_TITLE))
            note.message = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_MESSAGE))
            // note.date = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_DATE))
            note.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            note.important = cursor.getInt(cursor.getColumnIndex(TableInfo.COLUMN_NAME_IMPORTANT))

            if(note.important == 1){
                importantNotes.add(note)
            }
            cursor.moveToNext()
        }
        //Close cursor and database
        cursor.close()
        db.close()

        return importantNotes
    }
}