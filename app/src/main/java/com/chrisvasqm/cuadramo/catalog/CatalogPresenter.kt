package com.chrisvasqm.cuadramo.catalog

import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

class CatalogPresenter : CatalogContract.Presenter {
    private var view: CatalogContract.View? = null

    override fun attach(view: CatalogContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadCatalog() {
        // TODO: Pending for refactor to use a repository to DRY out the userId and querying logic.
        val database = FirebaseDatabase.getInstance()
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid
        val cuadresReference = database.getReference("users/$userId/cuadres")
        val cuadres = mutableListOf<Cuadre>()
        cuadresReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                cuadres.clear()
                snapshot.children.forEach {
                    val cuadre = it.getValue(Cuadre::class.java)
                    cuadre?.let { c -> cuadres.add(c) }
                }

                view?.showCatalog(cuadres)
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.e(error.message)
            }
        })
    }

    override fun signOut() {
        view?.showSignOutDialog()
    }

    override fun rate() {
        view?.rate()
    }

}
