package com.chrisvasqm.cuadramo.catalog

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_catalog.*
import kotlinx.android.synthetic.main.toolbar.*

class CatalogActivity : AppCompatActivity(), CatalogContract.View {

    private lateinit var auth: FirebaseAuth

    private lateinit var client: GoogleSignInClient

    private lateinit var presenter: CatalogContract.Presenter

    private lateinit var router: CatalogContract.Router

    private lateinit var firebaseDatabase: FirebaseDatabase

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalog)
        setSupportActionBar(toolbar)
        presenter = CatalogPresenter().apply { attach(this@CatalogActivity) }
        presenter.loadCatalog()
        router = CatalogRouter(this)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        client = GoogleSignIn.getClient(this, gso)

        fabAdd.setOnClickListener {
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_sign_out -> presenter.signOut()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showCatalog(cuadres: MutableList<Cuadre>) {
        setupRecyclerView(cuadres)
    }

    private fun setupRecyclerView(cuadres: MutableList<Cuadre>) {
        catalogRecyclerView.apply {
            setHasFixedSize(true)
            adapter = CatalogAdapter(cuadres)
            layoutManager = LinearLayoutManager(this@CatalogActivity)
            addItemDecoration(DividerItemDecoration(this@CatalogActivity, DividerItemDecoration.VERTICAL))
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
                finish()
            }
        }
        setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int -> }
    }
}
