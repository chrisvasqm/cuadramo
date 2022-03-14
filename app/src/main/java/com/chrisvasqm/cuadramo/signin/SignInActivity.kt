package com.chrisvasqm.cuadramo.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.catalog.CatalogActivity
import com.chrisvasqm.cuadramo.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import timber.log.Timber

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private val TAG: String = this::class.java.simpleName

    private val RC_SIGN_IN: Int = 9001

    private lateinit var client: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        client = GoogleSignIn.getClient(this, gso)

        binding.btnSignIn.setOnClickListener { signIn() }
    }

    override fun onStart() {
        super.onStart()
        updateUi(FirebaseAuth.getInstance().currentUser)
    }

    private fun signIn() {
        val intent = client.signInIntent
        startActivityForResult(intent, RC_SIGN_IN)
    }

    private fun updateUi(user: FirebaseUser?) {
        val isLoggedIn = user != null
        if (isLoggedIn) goToCatalog()
    }

    private fun goToCatalog() {
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
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
                val auth = FirebaseAuth.getInstance()
                auth.signInWithCredential(credential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            updateUi(auth.currentUser)
                        } else {
                            showMessage("Authentication failed.")
                            updateUi(null)
                        }
                    }
            } catch (exception: ApiException) {
                Timber.w(TAG, "Google Sign In failed: $exception")
                Snackbar.make(binding.signInLayout, "Google sign in failed.", Snackbar.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.signInLayout, message, Snackbar.LENGTH_SHORT).show()
    }

}
