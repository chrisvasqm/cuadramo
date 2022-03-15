package com.chrisvasqm.cuadramo.view.dialogs

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.BottomSheetItemOptionsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
        // TODO: Replace once the Room database is implemented
        Toast.makeText(requireContext(), "To be added", Toast.LENGTH_LONG).show()
    }

    private fun showDeletionDialog() {
        setupDeletionDialog().show()
    }

    private fun setupDeletionDialog(): AlertDialog.Builder =
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.delete)
            setMessage(getString(R.string.not_recover))
            setPositiveButton(R.string.delete) { _: DialogInterface, _: Int ->
                deleteItem()
            }
            setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int -> }
        }


}