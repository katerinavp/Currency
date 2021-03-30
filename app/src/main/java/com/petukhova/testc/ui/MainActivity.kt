package com.petukhova.testc.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.petukhova.testc.R
import com.petukhova.testc.ui.fragment.ConvertFragment
import com.petukhova.testc.ui.fragment.MainFragment
import com.petukhova.testc.util.replaceFragment


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.replaceFragment(MainFragment())

    }

}
