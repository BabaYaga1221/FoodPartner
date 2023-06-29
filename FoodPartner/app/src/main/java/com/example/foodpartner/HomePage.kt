package com.example.foodpartner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.foodpartner.fragments.FavouritesFragment
import com.example.foodpartner.fragments.HomePageFragment
import com.example.foodpartner.fragments.ProfileFragment
import com.example.foodpartner.fragments.QnaFragment
import com.google.android.material.navigation.NavigationView

class HomePage : AppCompatActivity() {

    lateinit var drawerLayout:DrawerLayout
    lateinit var coordinatorLayout:CoordinatorLayout
    lateinit var toolBar:androidx.appcompat.widget.Toolbar
    lateinit var frameLayout:FrameLayout
    lateinit var navigationLayout: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolBar = findViewById(R.id.Toolbar)
        frameLayout = findViewById(R.id.frame)
        navigationLayout = findViewById(R.id.navigationLayout)

        setUpToolBar()

        openHomePage()

        var previousMenuItem: MenuItem? = null
        previousMenuItem?.isCheckable=true
        previousMenuItem?.isChecked=true

        val actionBarDrawerToggle = ActionBarDrawerToggle(this@HomePage,drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navigationLayout.setNavigationItemSelectedListener{

            if(previousMenuItem != null)
            {
                previousMenuItem?.isChecked = false
            }

            it.isCheckable = true
            it.isChecked = true
            previousMenuItem = it

            when(it.itemId)
            {
                R.id.Homepage ->{
                    openHomePage()
                    drawerLayout.closeDrawers()
                }
                R.id.Favourites ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, FavouritesFragment())
                        .commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Favorites"
                }
                R.id.ProfilePage ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, ProfileFragment())
                        .commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="Profile"
                }
                R.id.QnA ->{
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame, QnaFragment())
                        .commit()
                    drawerLayout.closeDrawers()
                    supportActionBar?.title="FAQs"
                }
            }

            return@setNavigationItemSelectedListener true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if(id == android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    fun setUpToolBar()
    {
        setSupportActionBar(toolBar)
        supportActionBar?.title = "Toolbar Title"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun openHomePage()
    {
        supportFragmentManager.beginTransaction().replace(R.id.frame, HomePageFragment()).commit()
        supportActionBar?.title = "Home Page"
    }

    override fun onBackPressed() {
        val frag = supportFragmentManager.findFragmentById(R.id.frame)

        when(frag)
        {
            !is HomePageFragment -> {
                openHomePage()
            }

            else -> super.onBackPressed()
        }

        drawerLayout.closeDrawers()
    }

}