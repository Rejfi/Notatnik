package com.revolshen.notekeeper

interface FragmentInterface {

    fun setDataRecyclerView(notes: ArrayList<Note>){}

    fun receiveDataFromDataBase(): ArrayList<Note>
}