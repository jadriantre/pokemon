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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var splash: androidx.core.splashscreen.SplashScreen
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater )
        setContentView(binding.root)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?){
        if( savedInstanceState == null ) {
            splash.setKeepOnScreenCondition { true }
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nhf) as NavHostFragment
            val navController: NavController = navHostFragment.navController
            mainActivityViewModel.characters.observe(this, Observer { it ->
                splash.setKeepOnScreenCondition { false }
                navController.navigate(R.id.charactersFragment, null, Animation.navOptions)
                navController.popBackStack()
                mainActivityViewModel.setCharacters(it)
            })
            mainActivityViewModel.tryAgain.observe( this, Observer { it ->
                if(it)
                    splash.setKeepOnScreenCondition { false }
            } )
            mainActivityViewModel.getCharacters()
        }
    }

}