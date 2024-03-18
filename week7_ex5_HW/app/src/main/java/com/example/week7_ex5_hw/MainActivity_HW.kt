package com.example.week7_ex5_hw


import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.week7_ex5_hw.databinding.ActivityEx4Binding
import com.example.week7_ex5_hw.databinding.ItemPagerBinding

class MainActivity_HW : AppCompatActivity() {
    lateinit var toqqle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityEx4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val datas = mutableListOf<String>()
        for (i in 1..2){datas.add("Item $i")}

// ViewPager2 초기화
/*        val adapter = MyAdapter(datas)
        binding.viewpager.adapter = adapter
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL*/

        toqqle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toqqle.syncState()
        /*
            프래그먼트 관련 코드
         */
        val fragButton = findViewById<Button>(R.id.fragBut1)
        val fragmentManager : FragmentManager = supportFragmentManager
        var onClicked = false
        fragButton.setOnClickListener {
            if (onClicked) {
                onClicked = false
                val transaction : FragmentTransaction = fragmentManager.beginTransaction()
                val frameLayout = supportFragmentManager.findFragmentById(R.id.fragment_content)
                transaction.remove(frameLayout!!).commit()
            }
            else {
                onClicked = true
                val transaction : FragmentTransaction = fragmentManager.beginTransaction()
                transaction.add(R.id.fragment_content, OneFragment()).commit()
            }
        }



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toqqle.onOptionsItemSelected(item)) {return true}
        return super.onOptionsItemSelected(item)
    }

}

