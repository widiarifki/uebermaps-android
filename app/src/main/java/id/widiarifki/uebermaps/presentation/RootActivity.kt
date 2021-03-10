package id.widiarifki.uebermaps.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
    }

    fun navigateRootHostTo(destId: Int, args: Bundle? = null) {
        findNavController(R.id.rootNavHostFragment).navigate(destId, args)
    }

}