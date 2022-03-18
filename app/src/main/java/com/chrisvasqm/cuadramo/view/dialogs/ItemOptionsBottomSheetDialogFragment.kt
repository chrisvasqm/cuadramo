package com.chrisvasqm.cuadramo.view.dialogs

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.BottomSheetItemOptionsBinding
import com.chrisvasqm.cuadramo.view.catalog.CatalogViewModel
import com.chrisvasqm.cuadramo.view.editor.EditorActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemOptionsBottomSheetDialogFragment(
    private val cuadre: Cuadre,
    private val manager: FragmentManager
) : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetItemOptionsBinding

    private val TAG = this::class.java.simpleName

    private val viewModel: CatalogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetItemOptionsBinding.inflate(inflater, container, false)

        binding.optionPreview.setOnClickListener { previewItem() }

        binding.optionEdit.setOnClickListener { goToEditorScreen() }

        binding.optionDelete.setOnClickListener { showDeletionDialog() }

        return binding.root
    }

    private fun goToEditorScreen() {
        val intent = Intent(requireActivity(), EditorActivity::class.java)
        intent.putExtra("Cuadre", cuadre)
        startActivity(intent)
    }

    private fun previewItem() {
        PreviewBottomSheetDialogFragment(cuadre)
            .apply { isPreview = true }
            .show(manager, TAG)

        dismiss()
    }

    private fun showDeletionDialog() {
        setupDeletionDialog().show()
    }

    private fun setupDeletionDialog(): AlertDialog.Builder =
        AlertDialog.Builder(requireContext()).apply {
            setTitle(R.string.delete)
            setMessage(getString(R.string.not_recover))
            setPositiveButton(R.string.delete) { _: DialogInterface, _: Int ->
                viewModel.remove(cuadre)
                viewModel.fetch()
                dismiss()
            }
            setNegativeButton(android.R.string.cancel) { _: DialogInterface, _: Int ->
                dismiss()
            }
        }


}