package com.example.nima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nima.databinding.ActivityListItemBinding
import com.google.firebase.database.FirebaseDatabase

class ListTime : AppCompatActivity() ,Adapter.ClickListener{
    lateinit var binding: ActivityListItemBinding
    private val vm : ViewModel by viewModels()

    var ListAdapter:Adapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id =intent.getStringExtra("id").toString()
        val data = intent.getStringExtra("data").toString()

        binding.recItem.layoutManager=  LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        ListAdapter= Adapter(this)
        binding.recItem.adapter=ListAdapter
        getData(id,data)

    }

    fun getData(id:String,data:String){

                vm.searchTimeVM(id,data)?.observe(this, Observer {
                   ListAdapter?.loadListToAdapter(it)
                })

    }

}