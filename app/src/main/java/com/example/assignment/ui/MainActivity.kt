package com.example.assignment.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // injecting the mainviewmodel into main activity
    // binding this activity to the main activity xlm
    val viewModel by viewModels<MainViewModel>()
    lateinit var binding : ActivityMainBinding
    lateinit var adapter: AdapterRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        binding.artistInfo = viewModel

        // set the adapter with the adapter class I created and have it as linearlayout
        adapter= AdapterRecyclerView()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        init()
    }

    private fun init() {

        // observing the list<Artist> and update the ui accordingly.
        // toast a message that api call successfully returned a valid result
        // set the data to be shown inside the recycler view
        // hide the progress bar
        viewModel.artistList.observe(this){
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
            adapter.setData(it)
            binding.progress = false
        }

        // observing the error string and update the ui accordingly.
        // toast a massage of the error message I posted inside the viewmodel
        viewModel.error.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            Log.d("abc", it)
        }

        // observe the progressbar if it returns true of false
        // and update the visibility of progressbar accordingly.
        viewModel.progress.observe(this){
            binding.progress = it
        }
    }
}