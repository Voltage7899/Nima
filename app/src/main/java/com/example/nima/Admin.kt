package com.example.nima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.nima.databinding.ActivityAdminBinding
import com.example.nima.databinding.ActivityListItemBinding

class Admin : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private val vm : ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signSign.setOnClickListener {
            try {

                vm.signVM(binding.phoneSign.text.toString(),binding.passSign.text.toString()).observe(this,
                    Observer {
                        if (it){
                            startActivity(Intent(this,AddDelete::class.java))
                        }
                        else{
                            Toast.makeText(this,"Данные неверны",Toast.LENGTH_LONG).show()
                        }
                    })

            }
            catch (ex:Exception){
                Toast.makeText(this,"Проверьте введенные данные",Toast.LENGTH_LONG).show()
            }
        }
    }
}