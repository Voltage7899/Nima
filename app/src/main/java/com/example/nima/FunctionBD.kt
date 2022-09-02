package com.example.nima

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class FunctionBD {
    object staticFun{


        val listRaceTime:MutableLiveData<ArrayList<Race>> =MutableLiveData()

        val database:DatabaseReference= FirebaseDatabase.getInstance().getReference()

        fun searchTimeDB(id:String,data:String): LiveData<ArrayList<Race>>? {
            database.child("Race").child(id).child(data).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val commonList=ArrayList<Race>()
                    for (item in snapshot.children){
                        var race=item.getValue(Race::class.java)
                        if (race != null) {
                            commonList.add(race)
                        }
                    }
                    listRaceTime.value=commonList

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
            return  listRaceTime
        }

        fun addItemDB(race:Race){
            database.child("Race").child(race.id.toString()).child(race.data.toString()).addListenerForSingleValueEvent(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    database.child("Race").child(race.id.toString()).child(race.data.toString()).child(race.timeStart.toString()).setValue(race)
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

        fun sign(phone:String,pass:String):LiveData<Boolean>{
    val status = MutableLiveData<Boolean>()
    database.child("User").addListenerForSingleValueEvent(object :
        ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.child(phone).exists()) {
                val userCurrentData: User? = snapshot.child(phone).getValue(
                    User::class.java
                )

                if (userCurrentData?.phone.equals(phone) && userCurrentData?.pass.equals(pass)) {
                    status.value=true
                } else {

                }
            }
            else{


            }
        }

        override fun onCancelled(error: DatabaseError) {}
    })
    return status
}
        }
    }
