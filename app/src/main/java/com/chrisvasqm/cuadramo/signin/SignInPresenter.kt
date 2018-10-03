package com.chrisvasqm.cuadramo.signin

import com.google.firebase.auth.FirebaseUser

class SignInPresenter(private val view: SignInContract.View) : SignInContract.Presenter {

    override fun signIn() {
        view.signIn()
    }

    override fun updateUi(user: FirebaseUser?) {
        view.updateUi(user)
    }

}
