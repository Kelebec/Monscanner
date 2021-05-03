package com.taylorgameco.monscanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rita.RiTa

public class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val animals = mutableListOf<String>()
        applicationContext.assets.open("animals.txt").bufferedReader().useLines {
            lines -> lines.forEach { animals.add(it) }
        }

        val mon1 = Mon("marshmallow",animals)

    }
}