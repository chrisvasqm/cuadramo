package com.chrisvasqm.cuadramo.signin

import com.chrisvasqm.cuadramo.data.source.user.UserRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignInPresenter(private val view: SignInContract.View) : SignInContract.Presenter {

    private val auth = FirebaseAuth.getInstance()
    private val repository = UserRepository

    override fun signIn() {
        view.signIn()
    }

    override fun updateUi() {
        view.updateUi(repository.getCurrentUser())
    }

    override fun authWithGoogle(account: GoogleSignInAccount?) {
        view.logAccountId(account?.id)

        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view.logLoginSuccessfully()
                    view.updateUi(auth.currentUser)
                } else {
                    view.logLoginFailure(task.exception)
                    view.showMessage("Authentication failed.")
                    view.updateUi(null)
                }
            }
    }
}
