package com.chrisvasqm.cuadramo.catalog

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.chrisvasqm.cuadramo.databinding.ActivityCatalogBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.toolbar.*

class CatalogActivity : AppCompatActivity(), CatalogContract.View {

    private lateinit var binding: ActivityCatalogBinding

    private lateinit var auth: FirebaseAuth

    private lateinit var client: GoogleSignInClient

    private lateinit var presenter: CatalogContract.Presenter

    private lateinit var router: CatalogContract.Router


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatalogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)

        presenter = CatalogPresenter().apply { attach(this@CatalogActivity) }
        presenter.loadCatalog()

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        client = GoogleSignIn.getClient(this, gso)

        router = CatalogRouter(this)
        binding.fabAdd.setOnClickListener {
            router.goToEditorScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cuadres_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_sign_out -> presenter.signOut()
            R.id.item_rate -> presenter.goToPlayStore()
            R.id.item_about -> presenter.goToAboutScreen()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showCatalog(cuadres: MutableList<Cuadre>) {
        // TODO: Remove Toast after implementing Room database
        Toast.makeText(this, "WIP: Removed Firebase Database", Toast.LENGTH_LONG).show()
        setupRecyclerView(cuadres)
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

    override fun showSignOutDialog() {
        val dialog = setupSignOutDialog()
        dialog.show()
    }

    private fun setupSignOutDialog(): AlertDialog.Builder = AlertDialog.Builder(this).apply {
        setTitle(R.string.sign_out)
        setMessage(getString(R.string.have_to_sign_in_again))
        setPositiveButton(R.string.sign_out) { _: DialogInterface, _: Int ->
            client.signOut().addOnCompleteListener {
                auth.signOut()
                goToSignInScreen()
            }
        }
        setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int -> }
    }

    override fun rate() {
        router.goToPlayStore()
    }

    override fun goToAboutScreen() {
        router.goToAboutScreen()
    }

    override fun goToSignInScreen() {
        router.goToSignInScreen()
    }


}
