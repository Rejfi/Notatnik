package com.revolshen.notekeeper.Adapters

import android.content.Intent
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.revolshen.notekeeper.Classes.DetailsActivity
import com.revolshen.notekeeper.DataClass.Note
import com.revolshen.notekeeper.R
import com.revolshen.notekeeper.SQLDataBaseHelper
import com.revolshen.notekeeper.TableInfo
import kotlinx.android.synthetic.main.note_view.view.*

class CardViewAdapter(val notes: ArrayList<Note>): RecyclerView.Adapter<MyViewHolder>(){
    companion object{
        val EDIT_CODE = "EDIT_CODE"
        val TITIE_CODE = "TITLE_CODE"
        val MESSAGE_CODE = "MESSAGE_CODE"
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val view_note = layoutInflater.inflate(R.layout.note_view, p0, false)
        return MyViewHolder(view_note)

    }
    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Elements of one Note
            val title = holder.view.title_cardView
            val message = holder.view.message_cardView
            val date = holder.view.date_note
            val cardView = holder.view.note_cardView

        //Set the date from notes
            title.text = notes[holder.adapterPosition].title
            message.text = notes[holder.adapterPosition].message
            date.text = notes[holder.adapterPosition].date


        //Delete note after long click -- remove from DateBase and from ArrayList<Note>
        cardView.setOnLongClickListener {
            val dbHelper = SQLDataBaseHelper(holder.view.context)
            val db = dbHelper.writableDatabase

            db.delete(
                TableInfo.TABLE_NAME,
                BaseColumns._ID + "=?",
                arrayOf(notes[holder.adapterPosition].id.toString()))
            notes.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)

            db.close()
            true
        }

        //Edit note after onClick. Load data and open DetailActivity
        cardView.setOnClickListener{
                val intent = Intent(holder.view.context, DetailsActivity::class.java)
                intent.apply {
                    putExtra(EDIT_CODE, EDIT_CODE)
                    putExtra(TITIE_CODE, title.text.toString())
                    putExtra(MESSAGE_CODE, message.text.toString())
                }
                holder.view.context.startActivity(intent)
        }

    }
}
class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)