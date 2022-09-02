package com.example.nima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.nima.databinding.ActivityAddBinding
import com.example.nima.databinding.ActivityAddDeleteBinding

class Add : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private val vm : ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addAdd.setOnClickListener {
            try {
                val id = binding.addFrom.text.toString().substring(0,2) + binding.addToo.text.toString().substring(0,2)
                val race=Race(id,binding.addData.text.toString(),binding.addStartTime.text.toString(),binding.addFinishTime.text.toString(),binding.addFrom.text.toString(),binding.addToo.text.toString())
                vm.addItem(race)
                finish()
            }
            catch (ex:Exception){
                Toast.makeText(this,"Проверьте введенные данные", Toast.LENGTH_LONG).show()
            }

        }
    }
}