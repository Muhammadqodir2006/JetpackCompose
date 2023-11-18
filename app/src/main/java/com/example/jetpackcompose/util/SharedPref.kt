package com.example.jetpackcompose.util

import android.content.Context

class SharedPref private constructor(context: Context) {
    private val shared = context.getSharedPreferences("app_shared", 0)
    private val edit = shared.edit()

    companion object{
        private var instance : SharedPref? = null
        fun getInstance(context: Context): SharedPref? {
            if (instance == null) instance = SharedPref(context)
            return instance
        }
    }

    fun getHighScore(level: Int){
        shared.getInt("hs$level", 0)
    }

    fun setHighScore(level: Int, score:Int){
        edit.putInt("hs$level", score).apply()
    }
}