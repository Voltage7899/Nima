package com.example.nima

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ViewModel :ViewModel() {

    fun searchTimeVM(id:String,data: String):LiveData<ArrayList<Race>>?{
        return FunctionBD.staticFun.searchTimeDB(id,data)
    }

    fun signVM(phone:String,pass:String):LiveData<Boolean>{
      return  FunctionBD.staticFun.sign(phone,pass)
    }
    fun addItem(race:Race){
        FunctionBD.staticFun.addItemDB(race)
    }
//    fun getItemVM(id:String,data:String,time:String):LiveData<Race>?{
//        return FunctionBD.staticFun.getItemDB(id,data,time)
//    }
}