package com.chrisvasqm.cuadramo.editor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher


import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.chrisvasqm.cuadramo.extensions.clear
import com.chrisvasqm.cuadramo.extensions.toInt
import com.chrisvasqm.cuadramo.ui.PreviewBottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.toolbar.*

class EditorActivity : AppCompatActivity(), EditorContract.View {

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
            btnClear.isEnabled = isAnyFieldFilled
        }
    }

    private val cuadrarWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btnCuadrar.isEnabled = inputTicketsTotal.text.toInt() > inputTicketsLeft.text.toInt()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = EditorPresenter().apply { attach(this@EditorActivity) }
        router = EditorRouter(this)

        btnClear.setOnClickListener { presenter.clearForm() }

        btnCuadrar.setOnClickListener { presenter.showPreview() }

        setupClearButtonWatcher()
        setupCuadrarButtonWatcher()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    private fun setupClearButtonWatcher() {
        inputCash.addTextChangedListener(clearWatcher)
        inputTicketsTotal.addTextChangedListener(clearWatcher)
        inputTicketsLeft.addTextChangedListener(clearWatcher)
        inputFood.addTextChangedListener(clearWatcher)
        inputFreebies.addTextChangedListener(clearWatcher)
        inputDelivery.addTextChangedListener(clearWatcher)
        inputOthers.addTextChangedListener(clearWatcher)
    }

    private fun setupCuadrarButtonWatcher() {
        inputTicketsTotal.addTextChangedListener(cuadrarWatcher)
        inputTicketsLeft.addTextChangedListener(cuadrarWatcher)
    }

    override fun getCash(): Int = inputCash.text.toInt()

    override fun getTicketsTotal(): Int = inputTicketsTotal.text.toInt()

    override fun getTicketsLeft(): Int = inputTicketsLeft.text.toInt()

    override fun getFood(): Int = inputFood.text.toInt()

    override fun getFreebies(): Int = inputFreebies.text.toInt()

    override fun getDelivery(): Int = inputDelivery.text.toInt()

    override fun getOthers(): Int = inputOthers.text.toInt()

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
                .apply { addCuadre(Cuadre()) }
                .show(supportFragmentManager, TAG)
    }

    override fun saveTemporaryCuadre() {
        undoCuadre = Cuadre(
                inputCash.text.toInt(),
                inputTicketsTotal.text.toInt(),
                inputTicketsLeft.text.toInt(),
                inputFood.text.toInt(),
                inputFreebies.text.toInt(),
                inputDelivery.text.toInt(),
                inputOthers.text.toInt()
        )
    }

    override fun displayUndoMessage() {
        Snackbar.make(editorCoordinatorLayout, getString(R.string.fields_cleared), Snackbar.LENGTH_LONG)
                .setAction("Undo") { restoreForm() }
                .show()
    }

    override fun restoreForm() {
        inputCash.setText(undoCuadre.cash.toString())
        inputTicketsTotal.setText(undoCuadre.ticketsTotal.toString())
        inputTicketsLeft.setText(undoCuadre.ticketsLeft.toString())
        inputFood.setText(undoCuadre.food.toString())
        inputFreebies.setText(undoCuadre.freebies.toString())
        inputDelivery.setText(undoCuadre.delivery.toString())
        inputOthers.setText(undoCuadre.extras.toString())
    }

}

