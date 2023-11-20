package com.example.jetpackcompose.util

import android.content.Context

class SharedPref private constructor(context: Context) {
    private val shared = context.getSharedPreferences("app_shared", 0)
    private val edit = shared.edit()

    companion object{
        private var instance : SharedPref? = null
        fun getInstance(context: Context): SharedPref {
            if (instance == null) instance = SharedPref(context)
            return instance!!
        }
    }

    fun getRecord(level: Int): Int{
        return shared.getInt("record$level", 0)
    }

    fun setRecord(level: Int, score:Int){
        edit.putInt("record$level", score).apply()
    }
}