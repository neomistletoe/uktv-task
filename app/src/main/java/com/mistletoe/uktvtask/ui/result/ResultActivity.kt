package com.mistletoe.uktvtask.ui.result

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mistletoe.uktvtask.R
import com.mistletoe.uktvtask.data.model.Film
import com.mistletoe.uktvtask.data.model.Transportation
import com.mistletoe.uktvtask.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private var resultList: MutableList<Any> = mutableListOf()
    private lateinit var infoListAdapter: InfoListAdapter
    private val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        handleIntentAndLoadData()
        initSpinnerListener()
    }

    // Init recyclerview
    private fun initRecyclerView() {
        binding.recyclerResults.layoutManager = LinearLayoutManager(this)
    }

    // Handle intent and load data
    private fun handleIntentAndLoadData() {
        val sharedPreferences = getSharedPreferences("search_data", MODE_PRIVATE)
        val jsonData = sharedPreferences.getString("result_list", null)
        val inputKeyword = intent.getStringExtra("input") ?: ""

        if (jsonData.isNullOrEmpty()) {
            Log.e("ResultActivity", "No data found in SharedPreferences")
            return
        }

        // Deserialize the data into objects using Gson
        resultList = when (inputKeyword) {
            "films" -> gson.fromJson(jsonData, Array<Film>::class.java).toMutableList()
            else -> gson.fromJson(jsonData, Array<Transportation>::class.java).toMutableList()
        }

        infoListAdapter = InfoListAdapter(resultList)
        binding.recyclerResults.adapter = infoListAdapter
        binding.textTitle.text = getString(R.string.results_found, resultList.size, inputKeyword)
    }

    // Init spinner listener
    private fun initSpinnerListener() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?, position: Int, id: Long
            ) {
                sortResults(ascending = position == 0)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    // Sort results by title/name
    private fun sortResults(ascending: Boolean) {
        val comparator: (Any) -> String? = {
            when (it) {
                is Film -> it.title
                is Transportation -> it.name
                else -> ""
            }
        }

        if (ascending) {
            resultList.sortBy(comparator)
        } else {
            resultList.sortByDescending(comparator)
        }

        infoListAdapter.notifyDataSetChanged()
    }
}
