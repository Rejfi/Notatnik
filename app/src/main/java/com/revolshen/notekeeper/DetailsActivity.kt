package com.revolshen.notekeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.save_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.saveBT_details){
            val dbHelper = SQLDataBaseHelper(applicationContext)
            val db = dbHelper.writableDatabase


            onBackPressed()


        }

        return super.onOptionsItemSelected(item)
    }
}