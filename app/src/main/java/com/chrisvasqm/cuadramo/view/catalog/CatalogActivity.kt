package com.chrisvasqm.cuadramo.view.catalog

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.ActivityCatalogBinding
import com.chrisvasqm.cuadramo.view.about.AboutActivity
import com.chrisvasqm.cuadramo.view.editor.EditorActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatalogBinding

    private val viewModel: CatalogViewModel by viewModels()

    private lateinit var catalogAdapter: CatalogAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel.fetchCuadres()

        viewModel.cuadres.observe(this) {
            setupCatalog(it)
        }

        binding.fabAdd.setOnClickListener { goToEditorScreen() }
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

    private fun goToEditorScreen() {
        Intent(this, EditorActivity::class.java).also { startActivity(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.catalog_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

    private fun goToAboutScreen() {
        Intent(this, AboutActivity::class.java).also { startActivity(it) }
    }

}
