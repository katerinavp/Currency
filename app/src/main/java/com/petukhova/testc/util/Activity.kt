package com.petukhova.testc.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.petukhova.testc.R

fun AppCompatActivity.replaceFragment(fragment: Fragment) {
    this.supportFragmentManager //builder
        .beginTransaction()
        .replace(R.id.fragmentContainer, fragment)
        .commit()
}