package com.chrisvasqm.cuadramo.signin

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseUser

interface SignInContract {

    interface View {

        fun updateUi(user: FirebaseUser?)

        fun signIn()

        fun goToCatalog()

        fun logAccountId(id: String?)

        fun logLoginSuccessfully()

        fun logLoginFailure(exception: Exception?)

        fun showMessage(message: String)

    }

    interface Presenter {

        fun signIn()

        fun updateUi()

        fun authWithGoogle(account: GoogleSignInAccount?)

    }

}