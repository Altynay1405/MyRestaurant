package com.example.navdrawerandlogin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userId = firebaseAuth.currentUser?.uid

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(userId.isNullOrEmpty()) {
            onSignout()
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)


        /*ActionBarDrawerToggle is for displaying an drawer indicator in the appbar which needs 5 arguments.

        Current Activity context
        DrawerLayout variable
                Toolbar variable
                Drawer open description message via Resource string
        Drawer close description message via Resource string
        I passed 0 value for both 4th & 5th, cause I dont need those.*/

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,0,0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){

            R.id.nav_home ->{
                Toast.makeText(this, "Home clicked", Toast.LENGTH_LONG).show()
            }

            R.id.nav_order ->{
                Toast.makeText(this, "Orders clicked", Toast.LENGTH_LONG).show()
            }

            R.id.nav_reward ->{
                Toast.makeText(this, "Rewards clicked", Toast.LENGTH_LONG).show()
            }

            R.id.nav_profile ->{
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_LONG).show()
            }

            R.id.nav_logout->{
                onSignout()
                Toast.makeText(this, "You have succesfully logout", Toast.LENGTH_LONG).show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }

     fun onSignout() {
        firebaseAuth.signOut()  //firebase auth sign out
        startActivity(LoginActivity.newIntent(this))
        finish()
    }

    companion object {
        fun newIntent(context: Context?) = Intent(context, MainActivity::class.java)
    }
}
