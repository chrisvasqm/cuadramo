package com.chrisvasqm.cuadramo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.chrisvasqm.cuadramo.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.BottomDialogCuadrePreviewBinding
import com.chrisvasqm.cuadramo.extensions.toString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PreviewBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: BottomDialogCuadrePreviewBinding

    private var cuadre = Cuadre()

    private lateinit var manager: FragmentManager

    var isPreview = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomDialogCuadrePreviewBinding.inflate(inflater, container, false)

        binding.textDate.text = cuadre.createdAt.toString("yyyy/MM/dd HH:mm:ss")
        binding.textCash.text = cuadre.cash.toString()
        binding.textDocuments.text = (cuadre.ticketsSold).toString()
        binding.textExpenses.text = cuadre.expenses.toString()
        binding.textResult.text = cuadre.revenue.toString()

        binding.btnSave.setOnClickListener {
            // TODO: Replace once the Room database is implemented
            Toast.makeText(requireContext(), "To be added", Toast.LENGTH_LONG).show()
        }

        // Hide the save button when the user is just checking an existing cuadre.
        if (isPreview) {
            binding.btnSave.visibility = View.GONE
        }

        return binding.root
    }

    fun setCuadre(cuadre: Cuadre) {
        this.cuadre = cuadre
    }

    fun setManager(manager: FragmentManager) {
        this.manager = manager
    }

}