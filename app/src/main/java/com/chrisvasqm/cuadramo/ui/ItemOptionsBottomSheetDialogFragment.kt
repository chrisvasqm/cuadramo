package com.chrisvasqm.cuadramo.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.chrisvasqm.cuadramo.databinding.BottomSheetItemOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

class ItemOptionsBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetItemOptionsBinding

    private val TAG = this::class.java.simpleName

    private var cuadre = Cuadre()

    private lateinit var manager: FragmentManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetItemOptionsBinding.inflate(inflater, container, false)

        binding.optionPreview.setOnClickListener { previewItem() }

        binding.optionDelete.setOnClickListener { showDeletionDialog() }

        return binding.root
    }

    fun setCuadre(cuadre: Cuadre) {
        this.cuadre = cuadre
    }

    fun setManager(manager: FragmentManager) {
        this.manager = manager
    }

    private fun previewItem() {
        PreviewBottomSheetDialogFragment().apply {
                setCuadre(cuadre)
                setManager(manager)
                isPreview = true
            }.show(manager, TAG)

        // To hide the BottomSheet before the next one opens
        dismiss()
    }

    private fun deleteItem() {
        val auth = FirebaseAuth.getInstance()
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val database = FirebaseDatabase.getInstance()
            val reference = database.getReference("users/$userId/cuadres")
            reference.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        val currentCuadre = child.getValue(Cuadre::class.java)
                        if (currentCuadre?.id == cuadre.id) {
                            child.ref.removeValue()

                            // To close the bottom sheet after removing an item.
                            dismiss()
                            break
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Timber.e("Firebase Database Deletion Error - $error")
                    Toast.makeText(
                        requireContext(),
                        "Something went wrong. Try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        }
    }

    private fun showDeletionDialog() {
        setupDeletionDialog().show()
    }

    private fun setupDeletionDialog(): AlertDialog.Builder = AlertDialog.Builder(requireContext()).apply {
        setTitle(R.string.delete)
        setMessage(getString(R.string.not_recover))
        setPositiveButton(R.string.delete) { _: DialogInterface, _: Int ->
            deleteItem()
        }
        setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int -> }
    }


}