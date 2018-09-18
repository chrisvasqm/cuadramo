package com.chrisvasqm.cuadramo

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        client = GoogleSignIn.getClient(this, gso)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.cuadres_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_sign_out -> signOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.sign_out)
            setMessage(getString(R.string.have_to_sign_in_again))
            setPositiveButton(R.string.sign_out) { _: DialogInterface, _: Int ->
                client.signOut().addOnCompleteListener {
                    auth.signOut()
                    finish()
                }
            }
            setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int -> }
        }.show()
    }
}
