package com.revolshen.notekeeper

import android.content.ContentValues
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.xmlpull.v1.XmlPullParser

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

                value.put(TableInfo.COLUMN_NAME_TITLE, title)
                value.put(TableInfo.COLUMN_NAME_MESSAGE, message)
                if(item.isChecked) value.put(TableInfo.COLUMN_NAME_IMPORTANT,1)
                else value.put(TableInfo.COLUMN_NAME_IMPORTANT,0)


                db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
                onBackPressed()

            }
            else onBackPressed()

        }
            if(item!!.itemId == R.id.importantBT){

                if(!item.isChecked)    {
                    item.setIcon(R.drawable.round_favorite_black_18dp)
                    item.setChecked(true)
                }

                else{
                    item.setIcon(R.drawable.round_favorite_border_black_18dp)
                    item.setChecked(false)
                }
            }

        return super.onOptionsItemSelected(item)


        TODO("Create feature can mark a notes as important and put it into database")
    }

}
