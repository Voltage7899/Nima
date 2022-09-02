package com.example.nima

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.nima.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.find.setOnClickListener {
            try {
                val id = binding.seOt.text.toString().substring(0,2) + binding.seKu.text.toString().substring(0,2)
                val data = binding.seData.text.toString()

                val intent =Intent(this,ListTime::class.java)
                intent.putExtra("id",id)
                intent.putExtra("data",data)
                startActivity(intent)

            }catch (ex:Exception){
                Toast.makeText(this,"Проверьте поля",Toast.LENGTH_LONG).show()
            }
        }
        binding.admin.setOnClickListener {
            startActivity(Intent(this,Admin::class.java))
        }
    }
}