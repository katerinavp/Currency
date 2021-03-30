package com.petukhova.testc.util

import androidx.fragment.app.Fragment
import com.petukhova.testc.R

fun Fragment.replaceFragment(fragment: Fragment) {
    requireActivity().supportFragmentManager
        .beginTransaction()
        .addToBackStack(null) //чтобы можно было вернутсья на предыдущий фрагмент
        .replace(R.id.fragmentContainer, fragment)
        .commit()
}