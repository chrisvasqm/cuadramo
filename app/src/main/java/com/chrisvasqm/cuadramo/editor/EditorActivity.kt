package com.chrisvasqm.cuadramo.editor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher


import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.chrisvasqm.cuadramo.databinding.ActivityEditorBinding
import com.chrisvasqm.cuadramo.extensions.clear
import com.chrisvasqm.cuadramo.extensions.toInt
import com.chrisvasqm.cuadramo.ui.PreviewBottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.toolbar.*

class EditorActivity : AppCompatActivity(), EditorContract.View {

    private lateinit var binding: ActivityEditorBinding

    private val TAG = "EditorActivity"

    private lateinit var presenter: EditorContract.Presenter

    private lateinit var router: EditorContract.Router

    private var undoCuadre = Cuadre()

    private val clearWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            val isAnyFieldFilled: Boolean = inputCash.text?.isNotBlank()!! ||
                    inputTicketsTotal.text?.isNotBlank()!! ||
                    inputTicketsLeft.text?.isNotBlank()!! ||
                    inputFood.text?.isNotBlank()!! ||
                    inputFreebies.text?.isNotBlank()!! ||
                    inputDelivery.text?.isNotBlank()!! ||
                    inputOthers.text?.isNotBlank()!!
            binding.btnClear.isEnabled = isAnyFieldFilled
        }
    }

    private val cuadrarWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            binding.btnCuadrar.isEnabled =
                inputTicketsTotal.text.toInt() > inputTicketsLeft.text.toInt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = EditorPresenter().apply { attach(this@EditorActivity) }
        router = EditorRouter(this)

        binding.btnClear.setOnClickListener { presenter.clearForm() }

        binding.btnCuadrar.setOnClickListener { presenter.showPreview() }

        setupClearButtonWatcher()
        setupCuadrarButtonWatcher()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
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

    override fun getCuadre(): Cuadre {
        return Cuadre(
            "",
            inputCash.text.toInt(),
            inputTicketsTotal.text.toInt(),
            inputTicketsLeft.text.toInt(),
            inputFood.text.toInt(),
            inputFreebies.text.toInt(),
            inputDelivery.text.toInt(),
            inputOthers.text.toInt()
        )
    }

    override fun clearForm() {
        saveTemporaryCuadre()

        val form = editorForm as ViewGroup
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

    override fun showPreview() {
        PreviewBottomSheetDialogFragment()
            .apply { setCuadre(getCuadre()) }
            .show(supportFragmentManager, TAG)
    }

    override fun saveTemporaryCuadre() {
        undoCuadre = getCuadre()
    }

    override fun displayUndoMessage() {
        Snackbar.make(
            editorCoordinatorLayout,
            getString(R.string.fields_cleared),
            Snackbar.LENGTH_LONG
        )
            .setAction(getString(R.string.undo)) { restoreForm() }
            .show()
    }

    override fun restoreForm() {
        binding.inputCash.setText(undoCuadre.cash.toString())
        binding.inputTicketsTotal.setText(undoCuadre.ticketsTotal.toString())
        binding.inputTicketsLeft.setText(undoCuadre.ticketsLeft.toString())
        binding.inputFood.setText(undoCuadre.food.toString())
        binding.inputFreebies.setText(undoCuadre.freebies.toString())
        binding.inputDelivery.setText(undoCuadre.delivery.toString())
        binding.inputOthers.setText(undoCuadre.extras.toString())
    }

}

