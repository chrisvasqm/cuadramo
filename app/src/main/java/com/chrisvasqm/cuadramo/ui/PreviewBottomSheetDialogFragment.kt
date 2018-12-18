package com.chrisvasqm.cuadramo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PreviewBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var cuadre: Cuadre? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_dialog_cuadre_preview, container, false)

        return view
    }

    override fun show(manager: FragmentManager?, tag: String?) {
        if (this.cuadre == null)
            throw IllegalArgumentException("Cuadre must not be null.")

        super.show(manager, tag)
    }

    fun addCuadre(cuadre: Cuadre) {
        this.cuadre = cuadre
    }

}