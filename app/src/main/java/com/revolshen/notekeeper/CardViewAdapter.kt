package com.revolshen.notekeeper

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_view.view.*

class CardViewAdapter(val notes: ArrayList<Note>): RecyclerView.Adapter<MyViewHolder>(){
    companion object{
        val EDIT_CODE = "EDIT_CODE"
        val TITIE_CODE = "TITLE_CODE"
        val MESSAGE_CODE = "MESSAGE_CODE"
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cardView_note = layoutInflater.inflate(R.layout.note_view, p0, false)
        return MyViewHolder(cardView_note)

    }
    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Elements of one Note
            val title = holder.view.title_cardView
            val message = holder.view.message_cardView
            val date = holder.view.date_note
            val note_cardView = holder.view.note_cardView
            val importantButton = holder.view.importantIcon

        //Set the date from notes
            title.setText(notes[holder.adapterPosition].title)
            message.setText(notes[holder.adapterPosition].message)
            date.setText(notes[holder.adapterPosition].date)


        note_cardView.setOnLongClickListener(object : View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                val dbHelper = SQLDataBaseHelper(holder.view.context)
                val db = dbHelper.writableDatabase

                db.delete(TableInfo.TABLE_NAME,
                    BaseColumns._ID + "=?",
                    arrayOf(notes[holder.adapterPosition].id.toString()))
                notes.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)

                db.close()
                return true
            }
        })

        note_cardView.setOnClickListener{
                val intent = Intent(holder.view.context, DetailsActivity::class.java)
                intent.apply {
                    putExtra(EDIT_CODE, EDIT_CODE)
                    putExtra(TITIE_CODE, title.text)
                    putExtra(MESSAGE_CODE, message.text)
                }
                holder.view.context.startActivity(intent)
        }

        importantButton.setOnClickListener {
            val dbHelper = SQLDataBaseHelper(holder.view.context)
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put(TableInfo.COLUMN_NAME_IMPORTANT, 1)

            db.update(TableInfo.TABLE_NAME,values, BaseColumns._ID + "=?", arrayOf(holder.adapterPosition.toString()))
            db.close()
        }

    }
}
class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)