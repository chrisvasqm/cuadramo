package com.chrisvasqm.cuadramo.signin

import com.google.firebase.auth.FirebaseUser

interface SignInContract {

    interface View {

        fun updateUi(user: FirebaseUser?)

        fun signIn()

        fun goToCatalog()

    }

    interface Presenter {

        fun signIn()

        fun updateUi(user: FirebaseUser?)

    }

}