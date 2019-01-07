package com.chrisvasqm.cuadramo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ItemOptionsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val TAG = "ItemOptionsBottomSheetDialogFragment"

    private var cuadre = Cuadre()

    private lateinit var manager: FragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_item_options, container, false)

        val optionPreview = view.findViewById<LinearLayout>(R.id.optionPreview)
        optionPreview.setOnClickListener { previewItem() }

        val optionDelete = view.findViewById<LinearLayout>(R.id.optionDelete)
        optionDelete.setOnClickListener { deleteItem() }

        return view
    }

    fun setCuadre(cuadre: Cuadre) {
        this.cuadre = cuadre
    }

    fun setManager(manager: FragmentManager) {
        this.manager = manager
    }

    private fun previewItem() {
        PreviewBottomSheetDialogFragment()
                .apply {
                    setCuadre(cuadre)
                    setManager(manager)
                    isPreview = true
                }
                .show(manager, TAG)

        // So that the ItemOptionsBottomSheet hides before the next one opens.
        dismiss()
    }

    private fun deleteItem() {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference("users/$userId/cuadres")

            // TODO: Delete the Cuadre selected
        }
    }


}