package com.chrisvasqm.cuadramo.view.catalog

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.ActivityCatalogBinding
import com.chrisvasqm.cuadramo.view.about.AboutActivity
import com.chrisvasqm.cuadramo.view.editor.EditorActivity
import com.chrisvasqm.cuadramo.view.signin.SignInActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding

    private lateinit var auth: FirebaseAuth

    private val viewModel: CatalogViewModel by viewModels()

    private lateinit var catalogAdapter: CatalogAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel.loadData()
        viewModel.cuadres.observe(this) {
            setupCatalog(it)
        }

        binding.fabAdd.setOnClickListener { goToEditorScreen() }

        // Redirect user to Sign In screen if they are not logged in
        auth = Firebase.auth
        redirectWhenNotLogged()
    }

    private fun setupCatalog(cuadres: List<Cuadre>) {
        if (cuadres.isNullOrEmpty()) {
            displayEmptyState()
            setupRecyclerView(mutableListOf())
        } else {
            displayEmptyState(false)
            setupRecyclerView(cuadres.toMutableList())
        }
    }

    private fun displayEmptyState(isDisplayed: Boolean = true) {
        if (isDisplayed) {
            binding.catalogRecyclerView.visibility = View.GONE
            binding.emptyViewLayout.root.visibility = View.VISIBLE
        } else {
            binding.emptyViewLayout.root.visibility = View.GONE
            binding.catalogRecyclerView.visibility = View.VISIBLE
        }
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

    private fun setupRecyclerView(cuadres: MutableList<Cuadre>) {
        val recyclerView = binding.catalogRecyclerView

        catalogAdapter = CatalogAdapter(cuadres, supportFragmentManager)
        recyclerView.adapter = catalogAdapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)

        val animation = AnimationUtils.loadLayoutAnimation(this, R.anim.layout_animation_fall_down)
        recyclerView.layoutAnimation = animation
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
