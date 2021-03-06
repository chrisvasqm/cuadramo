package com.chrisvasqm.cuadramo.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.catalog.CatalogActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_sign_in.*
import timber.log.Timber

class SignInActivity : AppCompatActivity(), SignInContract.View {

    private val TAG: String = this::class.java.simpleName

    private val RC_SIGN_IN: Int = 9001

    private lateinit var presenter: SignInContract.Presenter

    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        presenter = SignInPresenter().apply { attach(this@SignInActivity) }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        client = GoogleSignIn.getClient(this, gso)

        btnSignIn.setOnClickListener { presenter.signIn() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun onStart() {
        super.onStart()

        presenter.updateUi()
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

    override fun logAccountId(id: String?) {
        Timber.d(TAG, "authWithGoogle: $id")
    }

    override fun logLoginSuccessfully() {
        Timber.d(TAG, "signInWithCredential: success")
    }

    override fun logLoginFailure(exception: Exception?) {
        Timber.w(exception, "signInWithCredential:failure")
    }

    override fun showMessage(message: String) {
        Snackbar.make(signInLayout, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                val account = task?.getResult(ApiException::class.java)
                presenter.authWithGoogle(account)
            } catch (exception: ApiException) {
                Timber.w(TAG, "Google Sign In failed: $exception")
                Snackbar.make(signInLayout, "Google sign in failed.", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}
