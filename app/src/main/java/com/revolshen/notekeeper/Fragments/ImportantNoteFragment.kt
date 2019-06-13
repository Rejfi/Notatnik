package com.revolshen.notekeeper.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.revolshen.notekeeper.R


//Experimental class
class ImportantNoteFragment(): Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.important_note_fragment, container, false)
    }

}