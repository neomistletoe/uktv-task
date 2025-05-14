package com.mistletoe.uktvtask.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.mistletoe.uktvtask.data.model.Film
import com.mistletoe.uktvtask.data.service.StarWarsApiService
import com.mistletoe.uktvtask.data.model.Transportation
import com.mistletoe.uktvtask.databinding.ActivityMainBinding
import com.mistletoe.uktvtask.ui.result.ResultActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            // click search button
            buttonSearch.setOnClickListener {
                val inputKeyword = editSearch.text.toString()

                // handle user input
                handleUserInput(inputKeyword)
            }
        }
    }

    private fun callApi(inputKeyword: String) {

        // retrofit setting
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(StarWarsApiService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val starWarsApiService: StarWarsApiService =
            retrofit.create(StarWarsApiService::class.java)

        when (inputKeyword) {
            "films" -> {
                // films processing
                starWarsApiService.getFilms().enqueue(object : Callback<List<Film>> {
                    override fun onResponse(
                        call: Call<List<Film>>,
                        response: Response<List<Film>>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body() ?: emptyList()
                            moveToResult(data, inputKeyword)
                        }
                    }

                    override fun onFailure(call: Call<List<Film>>, t: Throwable) {
                        Log.e("API", "Film fetch failed", t)
                    }
                })
            }

            "starships", "vehicles" -> {
                // starships or vehicles processing
                starWarsApiService.getTransportations(inputKeyword)
                    .enqueue(object : Callback<List<Transportation>> {
                        override fun onResponse(
                            call: Call<List<Transportation>>,
                            response: Response<List<Transportation>>
                        ) {
                            if (response.isSuccessful) {
                                val data = response.body() ?: emptyList()
                                moveToResult(data, inputKeyword)
                            }
                        }

                        override fun onFailure(call: Call<List<Transportation>>, t: Throwable) {
                            Log.e("API", "Transportation fetch failed", t)
                        }
                    })
            }

        }
    }

    // Move to Result Activity
    private fun <T> moveToResult(data: List<T>, inputKeyword: String) {
        val jsonData = Gson().toJson(data)
        val sharedPreferences = getSharedPreferences("search_data", MODE_PRIVATE)
        sharedPreferences.edit().putString("result_list", jsonData).apply()

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("input", inputKeyword)
        startActivity(intent)
    }

    // Handle user input
    private fun handleUserInput(input: String) {
        val normalizedInput = input.trim().lowercase()
        val validInputs = listOf("films", "starships", "vehicles")

        if (normalizedInput in validInputs) {
            callApi(input)
        } else {
            showInvalidInputDialog()
        }
    }

    // Show invalid dialog
    private fun showInvalidInputDialog() {
        AlertDialog.Builder(this)
            .setTitle("Invalid Input")
            .setMessage("Please enter one of the following: films, starships, or vehicles.")
            .setPositiveButton("OK", null)
            .show()
    }
}
