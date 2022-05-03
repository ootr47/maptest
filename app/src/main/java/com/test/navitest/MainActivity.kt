package com.test.navitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.test.navitest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
	lateinit var binding: ActivityMainBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)


		//하단 네비게이션 바
		val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigatin_view)
		val navController = findNavController(R.id.nav_fragment)
		bottomNavigationView.setupWithNavController(navController)

		binding.btnMenu.setOnClickListener {
			binding.layoutDraw.openDrawer(GravityCompat.START) //START : left, END : right
		}
		binding.naviView.setNavigationItemSelectedListener(this)
	}
	override fun onNavigationItemSelected(item: MenuItem): Boolean {
		when(item.itemId){

		}
		binding.layoutDraw.closeDrawers()
		return false
	}


}