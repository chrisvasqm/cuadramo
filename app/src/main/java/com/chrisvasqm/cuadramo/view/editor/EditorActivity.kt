package com.chrisvasqm.cuadramo.view.editor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.databinding.ActivityEditorBinding
import com.chrisvasqm.cuadramo.extensions.toInt
import com.chrisvasqm.cuadramo.view.dialogs.PreviewBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditorBinding

    private val TAG = this::class.java.simpleName

    private val calculateWatcher = object : TextWatcher {
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

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnCuadrar.setOnClickListener { showPreview() }

        val editingCuadre = intent.getParcelableExtra<Cuadre>("Cuadre")
        if (editingCuadre != null)
            fillOutForm(editingCuadre)

        setupCuadrarButtonWatcher()
    }

    private fun fillOutForm(editingCuadre: Cuadre) {
        binding.inputCash.setText(editingCuadre.cash.toString())
        binding.inputTicketsTotal.setText(editingCuadre.ticketsTotal.toString())
        binding.inputTicketsLeft.setText(editingCuadre.ticketsLeft.toString())
        binding.inputFood.setText(editingCuadre.food.toString())
        binding.inputFreebies.setText(editingCuadre.freebies.toString())
        binding.inputDelivery.setText(editingCuadre.delivery.toString())
        binding.inputOthers.setText(editingCuadre.others.toString())
    }

    private fun setupCuadrarButtonWatcher() {
        binding.inputTicketsTotal.addTextChangedListener(calculateWatcher)
        binding.inputTicketsLeft.addTextChangedListener(calculateWatcher)
    }

    private fun createCuadre(): Cuadre {
        return Cuadre(
            cash = binding.inputCash.text.toInt(),
            ticketsTotal = binding.inputTicketsTotal.text.toInt(),
            ticketsLeft = binding.inputTicketsLeft.text.toInt(),
            food = binding.inputFood.text.toInt(),
            freebies = binding.inputFreebies.text.toInt(),
            delivery = binding.inputDelivery.text.toInt(),
            others = binding.inputOthers.text.toInt()
        )
    }

    private fun showPreview() {
        PreviewBottomSheetDialogFragment(createCuadre()).show(supportFragmentManager, TAG)
    }

}

