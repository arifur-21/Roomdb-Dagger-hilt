package com.example.daggerhiltroomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.each_row.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  val userViewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var name: TextView
    private lateinit var age: TextView
    private lateinit var save: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        name = findViewById(R.id.nameId)
        age = findViewById(R.id.poneId)
        save = findViewById(R.id.saveBtnId)

        setRecycleView()


        ///set data adapter
        userViewModel.getUserData.observe(this, Observer {
            userAdapter.setData(it as ArrayList<User>)
        })

        //insert data room database
        save.setOnClickListener {
            insertIntoRoom()
        }

    }

    private fun insertIntoRoom() {
        val uname = name.text.toString()
        val uage = age.text.toString()
        val user = User(0,uname, uage.toInt())
        userViewModel.insert(user)
    }


    private fun setRecycleView() {
        recyclerView = findViewById(R.id.recycelViewId)
        userAdapter = UserAdapter(this, ArrayList())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

    }
}