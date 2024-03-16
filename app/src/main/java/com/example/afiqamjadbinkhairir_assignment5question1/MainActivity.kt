package com.example.afiqamjadbinkhairir_assignment5question1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private val images = arrayOf(R.drawable.cr7, R.drawable.mu, R.drawable.lm10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById<Button>(R.id.Button)
        imageView = findViewById<ImageView>(R.id.ImageView)
        editText = findViewById<EditText>(R.id.EditText)

        val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
        editText.setText(sharedPreferences.getString("text", ""))
        val savedImageResID = sharedPreferences.getInt("imageResID", images[0])
        imageView.setImageResource(savedImageResID)

        button.setOnClickListener{
            val randomIndex = Random.nextInt(images.size)
            imageView.setImageResource(images[randomIndex])
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val sharedPreferences = getSharedPreferences("preferenceFile", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("text", editText.text.toString())
        editor.putInt("imageResID", imageView.id)
        editor.apply()
    }
}