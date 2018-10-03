package com.chrisvasqm.cuadramo.data.source.user

import com.google.firebase.auth.FirebaseUser

interface UserDataSource {

    fun getCurrentUser(): FirebaseUser?

}