package com.example.pokemon.presentation.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pokemon.R
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.presentation.util.Animation
import com.example.pokemon.presentation.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var splash: androidx.core.splashscreen.SplashScreen
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navBar: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView(binding.root)
        init(savedInstanceState)
        menu()
    }

    private fun init(savedInstanceState: Bundle?){
        if( savedInstanceState == null ) {
            splash.setKeepOnScreenCondition { true }
            mainActivityViewModel.characters.observe(this, Observer { it ->
                splash.setKeepOnScreenCondition { false }
                mainActivityViewModel.setCharacters(it)
            })
            mainActivityViewModel.tryAgain.observe( this, Observer { it ->
                if(it)
                    splash.setKeepOnScreenCondition { false }
            } )
            mainActivityViewModel.getCharacters()
        }
    }

    private fun menu(){
        navBar = binding.bn
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nhf) as NavHostFragment
        navController = navHostFragment.navController
        navBar.setOnItemSelectedListener { it ->
            navController.popBackStack()
            when(it.itemId) {
                R.id.imCharacters -> {
                    navController.navigate(R.id.charactersFragment, null, Animation.navOptions)
                    it.isChecked = true
                    true
                }
                R.id.imLocations -> {
                    navController.navigate(R.id.locationFragment, null, Animation.navOptions)
                    it.isChecked = true
                    true
                }
                else -> false
            }

        }
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            when(destination.id){
                R.id.charactersFragment -> navBar.menu.findItem(R.id.imCharacters).isChecked = true
                R.id.locationFragment -> navBar.menu.findItem(R.id.imLocations).isChecked = true
            }
        }
    }

}