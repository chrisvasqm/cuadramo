package com.chrisvasqm.cuadramo.editor

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.extensions.clear
import com.chrisvasqm.cuadramo.extensions.toInt
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_editor.*
import kotlinx.android.synthetic.main.toolbar.*


class EditorActivity : AppCompatActivity(), EditorContract.View {
    private lateinit var presenter: EditorContract.Presenter

    private lateinit var router: EditorContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setSupportActionBar(toolbar)
        presenter = EditorPresenter().apply { attach(this@EditorActivity) }
        router = EditorRouter(this)

        btnClear.setOnClickListener { presenter.clearForm() }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    override fun getCash(): Int = inputCash.text.toInt()

    override fun getTicketsTotal(): Int = inputTicketsTotal.text.toInt()

    override fun getTicketsLeft(): Int = inputTicketsLeft.text.toInt()

    override fun getFood(): Int = inputFood.text.toInt()

    override fun getFreebies(): Int = inputFreebies.text.toInt()

    override fun getDelivery(): Int = inputDelivery.text.toInt()

    override fun getOthers(): Int = inputOthers.text.toInt()

    override fun clearForm() {
        val form = editorForm as ViewGroup
        var counter = 0
        while (counter < form.childCount) {
            val view = form.getChildAt(counter)
            if (view is TextInputLayout) {
                val frameLayout = view.getChildAt(0) as FrameLayout
                val input = frameLayout.getChildAt(0) as TextInputEditText
                input.clear()
            }

            counter++
        }
    }

}

