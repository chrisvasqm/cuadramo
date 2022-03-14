package com.chrisvasqm.cuadramo.editor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher


import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.ActivityEditorBinding
import com.chrisvasqm.cuadramo.extensions.clear
import com.chrisvasqm.cuadramo.extensions.toInt
import com.chrisvasqm.cuadramo.dialogs.PreviewBottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.toolbar.*

class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding

    private val TAG = this::class.java.simpleName

    private var undoCuadre = Cuadre()

    private val clearWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            val isAnyFieldFilled: Boolean = binding.inputCash.text?.isNotBlank()!! ||
                    binding.inputTicketsTotal.text?.isNotBlank()!! ||
                    binding.inputTicketsLeft.text?.isNotBlank()!! ||
                    binding.inputFood.text?.isNotBlank()!! ||
                    binding.inputFreebies.text?.isNotBlank()!! ||
                    binding.inputDelivery.text?.isNotBlank()!! ||
                    binding.inputOthers.text?.isNotBlank()!!

            binding.btnClear.isEnabled = isAnyFieldFilled
        }
    }

    private val cuadrarWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.btnCuadrar.isEnabled =
                binding.inputTicketsTotal.text.toInt() > binding.inputTicketsLeft.text.toInt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnClear.setOnClickListener { clearForm() }

        binding.btnCuadrar.setOnClickListener { showPreview() }

        setupClearButtonWatcher()
        setupCuadrarButtonWatcher()
    }

    private fun setupClearButtonWatcher() {
        binding.inputCash.addTextChangedListener(clearWatcher)
        binding.inputTicketsTotal.addTextChangedListener(clearWatcher)
        binding.inputTicketsLeft.addTextChangedListener(clearWatcher)
        binding.inputFood.addTextChangedListener(clearWatcher)
        binding.inputFreebies.addTextChangedListener(clearWatcher)
        binding.inputDelivery.addTextChangedListener(clearWatcher)
        binding.inputOthers.addTextChangedListener(clearWatcher)
    }

    private fun setupCuadrarButtonWatcher() {
        binding.inputTicketsTotal.addTextChangedListener(cuadrarWatcher)
        binding.inputTicketsLeft.addTextChangedListener(cuadrarWatcher)
    }

    private fun getCuadre(): Cuadre {
        return Cuadre(
            "",
            binding.inputCash.text.toInt(),
            binding.inputTicketsTotal.text.toInt(),
            binding.inputTicketsLeft.text.toInt(),
            binding.inputFood.text.toInt(),
            binding.inputFreebies.text.toInt(),
            binding.inputDelivery.text.toInt(),
            binding.inputOthers.text.toInt()
        )
    }

    private fun clearForm() {
        saveTemporaryCuadre()

        val form = binding.editorForm as ViewGroup
        var counter = 0
        while (counter < form.childCount) {
            val view = form.getChildAt(counter)
            if (view is TextInputLayout) {
                val frameLayout = view.getChildAt(0) as FrameLayout
                val input = frameLayout.getChildAt(0) as TextInputEditText
                input.clear()

                displayUndoMessage()
            }

            counter++
        }
    }

    private fun showPreview() {
        PreviewBottomSheetDialogFragment()
            .apply { setCuadre(getCuadre()) }
            .show(supportFragmentManager, TAG)
    }

    private fun saveTemporaryCuadre() {
        undoCuadre = getCuadre()
    }

    private fun displayUndoMessage() {
        Snackbar.make(
            binding.editorCoordinatorLayout,
            getString(R.string.fields_cleared),
            Snackbar.LENGTH_LONG
        )
            .setAction(getString(R.string.undo)) { restoreForm() }
            .show()
    }

    private fun restoreForm() {
        binding.inputCash.setText(undoCuadre.cash.toString())
        binding.inputTicketsTotal.setText(undoCuadre.ticketsTotal.toString())
        binding.inputTicketsLeft.setText(undoCuadre.ticketsLeft.toString())
        binding.inputFood.setText(undoCuadre.food.toString())
        binding.inputFreebies.setText(undoCuadre.freebies.toString())
        binding.inputDelivery.setText(undoCuadre.delivery.toString())
        binding.inputOthers.setText(undoCuadre.extras.toString())
    }

}

