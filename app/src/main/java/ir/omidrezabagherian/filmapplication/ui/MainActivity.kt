package ir.omidrezabagherian.filmapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import ir.omidrezabagherian.filmapplication.R
import ir.omidrezabagherian.filmapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val navController by lazy {
        findNavController(R.id.fragmentContainerViewMain)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        mainBinding.bottomNavigationViewMain.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.tabHome -> {
                    navController.navigate(R.id.homeFragment)
                }

                R.id.tabComingSoon -> {
                    navController.navigate(R.id.comingSoonFragment)
                }

                R.id.tabPlay -> {
                    navController.navigate(R.id.playerFragment)
                }
            }

            true
        }

        setContentView(mainBinding.root)
    }
}