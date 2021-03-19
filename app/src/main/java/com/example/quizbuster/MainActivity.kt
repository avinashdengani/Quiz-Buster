package com.example.quizbuster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    public fun onSubmitName(view: View){
        val name = findViewById<TextInputEditText>(R.id.txtName).text.toString()
        if(name.isEmpty()){
            Toast.makeText(this,"Please insert your Name!", Toast.LENGTH_SHORT).show()
    }else{
            val intent = Intent(this, quizActivity::class.java)
            intent.putExtra(Constants.USERNAME, name)
            startActivity(intent)
            finish()
        }
    }
}