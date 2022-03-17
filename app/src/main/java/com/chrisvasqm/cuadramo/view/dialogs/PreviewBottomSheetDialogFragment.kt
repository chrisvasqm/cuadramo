package com.chrisvasqm.cuadramo.view.dialogs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.BottomDialogCuadrePreviewBinding
import com.chrisvasqm.cuadramo.extensions.toString
import com.chrisvasqm.cuadramo.view.catalog.CatalogActivity
import com.chrisvasqm.cuadramo.view.catalog.CatalogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

class PreviewBottomSheetDialogFragment(
    private val cuadre: Cuadre
) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomDialogCuadrePreviewBinding

    var isPreview = false

    private val viewModel: CatalogViewModel by activityViewModels()

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
            viewModel.save(cuadre)
            goToCatalogScreen()
        }

        // Hide the save button when the user is just checking an existing cuadre.
        if (isPreview) {
            binding.btnSave.visibility = View.GONE
        }

        return binding.root
    }

    private fun goToCatalogScreen() {
        val intent = Intent(requireActivity(), CatalogActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}