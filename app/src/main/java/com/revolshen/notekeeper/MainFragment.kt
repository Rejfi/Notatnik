package com.revolshen.notekeeper

import android.os.AsyncTask
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.function.UnaryOperator


class MainFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        val dbHelper = SQLDataBaseHelper(requireContext())
        val db = dbHelper.writableDatabase

        //Receive data from database
        val cursor = db.query(TableInfo.TABLE_NAME, null, null,
            null, null, null, null)

        cursor.moveToFirst()

        val notes = ArrayList<Note>()
        while(!cursor.isAfterLast){
            val note = Note()
            note.title = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_TITLE))
            note.message = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_MESSAGE))
            // note.date = cursor.getString(cursor.getColumnIndex(TableInfo.COLUMN_NAME_DATE))
            note.id = cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            notes.add(note)
            cursor.moveToNext()
        }

        //Close cursor and database
        cursor.close()
        db.close()

        //Set layout and adapter for recycler view
        recycler_view.layoutManager = GridLayoutManager(requireContext(),2)
        recycler_view.adapter = CardViewAdapter(notes)


    }

}