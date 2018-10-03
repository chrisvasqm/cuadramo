package com.chrisvasqm.cuadramo.signin

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.chrisvasqm.cuadramo.CatalogActivity
import com.chrisvasqm.cuadramo.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity(), SignInContract.View {

    private val TAG: String = this::class.java.simpleName

    private lateinit var presenter: SignInContract.Presenter

    private val RC_SIGN_IN: Int = 9001

    private lateinit var client: GoogleSignInClient

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        presenter = SignInPresenter(this)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        client = GoogleSignIn.getClient(this, gso)

        btnSignIn.setOnClickListener { presenter.signIn() }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        presenter.updateUi(currentUser)
    }

    override fun signIn() {
        val intent = client.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun updateUi(user: FirebaseUser?) {
        val isLoggedIn = user != null
        if (isLoggedIn) goToCatalog()
    }

    override fun goToCatalog() {
        val intent = Intent(this, CatalogActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task?.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (exception: ApiException) {
                Log.w(TAG, "Google Sign In failed: $exception")
                Snackbar.make(signInLayout, "Google sign in failed.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account?.id)

        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "signInWithCredential:success")
                        val user = auth.currentUser
                        presenter.updateUi(user)
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                        Snackbar.make(signInLayout, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                        presenter.updateUi(null)
                    }
                }
    }

}
