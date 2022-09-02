package com.example.nima

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nima.databinding.ActivityAddDeleteBinding
import com.example.nima.databinding.ActivityAdminBinding
import com.google.firebase.database.*

class AddDelete : AppCompatActivity(),Adapter.ClickListener {
    private lateinit var binding: ActivityAddDeleteBinding
    private val vm : ViewModel by viewModels()
    var ListAdapter:Adapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recAdm.layoutManager=  LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        ListAdapter = Adapter(this)
        binding.recAdm.adapter=ListAdapter
        val simpleCallback =object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Race")
                val race =ListAdapter?.deleteItem(viewHolder.adapterPosition)
                database.child(race?.id.toString()).child(race?.data.toString()).addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (race != null) {
                            database.child(race.id.toString()).child(race.data.toString()).child(race.timeStart.toString()).removeValue()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
            }

        }
        val itemTouchHelper= ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.recAdm)

        binding.Update.setOnClickListener {
            try {
                vm.searchTimeVM(binding.adminId.text.toString(),binding.adminData.text.toString())?.observe(this, Observer {

                    ListAdapter?.loadListToAdapter(it)
                    Log.d(TAG,"Work")
                })
            }catch (ex:Exception){
                Toast.makeText(this,"Проверьте введенные данные", Toast.LENGTH_LONG).show()
        }

    }
        binding.newItem.setOnClickListener {
            startActivity(Intent(this,Add::class.java))
        }


}
}