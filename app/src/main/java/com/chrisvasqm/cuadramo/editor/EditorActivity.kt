package com.chrisvasqm.cuadramo.editor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
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
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
