package com.example.to_do_list

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do_list.FileHelper.readData
import com.example.to_do_list.FileHelper.writeData

class MainActivity : AppCompatActivity() {
    var editText: EditText? = null
    var button: Button? = null
    var listView: ListView? = null
    var itemList: ArrayList<String?>? = ArrayList()
    var arrayAdapter: ArrayAdapter<String?>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        listView = findViewById(R.id.list)
        itemList = readData(this)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, itemList!!)
        listView!!.setAdapter(arrayAdapter)
        button!!.setOnClickListener(View.OnClickListener {
            val itemName = editText!!.getText().toString()
            if (itemName != "") {
                itemList!!.add(itemName)
                editText!!.setText("")
                writeData(itemList, applicationContext)
                arrayAdapter!!.notifyDataSetChanged()
            }
        })
        listView!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            val alert = AlertDialog.Builder(this@MainActivity)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No") { dialog, which -> dialog.cancel() }
            alert.setPositiveButton("Yes") { dialog, which ->
                itemList!!.removeAt(position)
                arrayAdapter!!.notifyDataSetChanged()
                writeData(itemList, applicationContext)
            }
            val alertDialog = alert.create()
            alertDialog.show()
        }
    }
}