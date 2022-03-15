package com.chrisvasqm.cuadramo.view.catalog

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.view.about.AboutActivity
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.ActivityCatalogBinding
import com.chrisvasqm.cuadramo.view.editor.EditorActivity
import com.chrisvasqm.cuadramo.view.signin.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        showCatalog()

        binding.fabAdd.setOnClickListener { goToEditorScreen() }

        // Redirect user to Sign In screen if they are not logged in
        auth = Firebase.auth
        redirectWhenNotLogged()
    }

    override fun onStart() {
        super.onStart()
        redirectWhenNotLogged()
    }

    private fun redirectWhenNotLogged() {
        if (auth.currentUser == null) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
            return
        }
    }

    private fun goToEditorScreen() {
        Intent(this, EditorActivity::class.java).also { startActivity(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.cuadres_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_sign_out -> showSignOutDialog()
            R.id.item_rate -> goToPlayStore()
            R.id.item_about -> goToAboutScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showCatalog() {
        // TODO: Remove Toast after implementing Room database
        Toast.makeText(this, "WIP: Removed Firebase Database", Toast.LENGTH_LONG).show()
        setupRecyclerView(mutableListOf())
    }

    private fun setupRecyclerView(cuadres: MutableList<Cuadre>) {
        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        binding.catalogRecyclerView.apply {
            setHasFixedSize(true)
            adapter = CatalogAdapter(cuadres, supportFragmentManager)
            layoutManager = LinearLayoutManager(this@CatalogActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@CatalogActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutAnimation = animation
        }
    }

    private fun showSignOutDialog() {
        val dialog = setupSignOutDialog()
        dialog.show()
    }

    private fun setupSignOutDialog(): AlertDialog.Builder = AlertDialog.Builder(this).apply {
        setTitle(R.string.sign_out)
        setMessage(getString(R.string.have_to_sign_in_again))
        setPositiveButton(R.string.sign_out) { _: DialogInterface, _: Int ->
            auth.signOut()
            goToSignInScreen()
        }
        setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int -> }
    }

    private fun goToPlayStore() {
        val url = "https://play.google.com/store/apps/details?id=com.chrisvasqm.cuadramo"
        Intent(Intent.ACTION_VIEW)
            .apply { data = Uri.parse(url) }
            .also { startActivity(it) }
    }

    private fun goToAboutScreen() {
        Intent(this, AboutActivity::class.java).also { startActivity(it) }
    }

    private fun goToSignInScreen() {
        Intent(this, SignInActivity::class.java)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            .also { startActivity(it) }
    }


}
