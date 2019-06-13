package com.revolshen.notekeeper.Classes

import android.content.ContentValues
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.revolshen.notekeeper.Adapters.CardViewAdapter
import com.revolshen.notekeeper.R
import com.revolshen.notekeeper.SQLDataBaseHelper
import com.revolshen.notekeeper.TableInfo
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.DateFormat
import java.util.*

class DetailsActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        //If intent has EDIT_CODE load data from EXTRAS
        if(intent.hasExtra(CardViewAdapter.EDIT_CODE)){
            title_detail.setText(intent.getStringExtra(CardViewAdapter.TITIE_CODE))
            message_detail.setText(intent.getStringExtra(CardViewAdapter.MESSAGE_CODE))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == R.id.saveBT_details) {
            //Open database to put new note
            val dbHelper = SQLDataBaseHelper(applicationContext)
            val db = dbHelper.writableDatabase
            val value = ContentValues()

            val title = title_detail.text.toString()
            val message = message_detail.text.toString()


            //Check title and message. If they are both empty don't create new note
            if (!(title.isNullOrEmpty() && message.isNullOrEmpty())) {

                val time = Calendar.getInstance().time.time
                val currentDate = DateFormat.getDateInstance().format(time)

                value.put(TableInfo.COLUMN_NAME_TITLE, title)
                value.put(TableInfo.COLUMN_NAME_MESSAGE, message)
                value.put(TableInfo.COLUMN_NAME_DATE, currentDate)
                if(item.isChecked) value.put(TableInfo.COLUMN_NAME_IMPORTANT,1)
                else value.put(TableInfo.COLUMN_NAME_IMPORTANT,0)

                db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
                onBackPressed()

            }
            else onBackPressed()
        }
        return super.onOptionsItemSelected(item)

    }

}
