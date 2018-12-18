package com.chrisvasqm.cuadramo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre
import com.chrisvasqm.cuadramo.extensions.toString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PreviewBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private var cuadre = Cuadre()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.bottom_dialog_cuadre_preview, container, false)

        val textDate = view?.findViewById<TextView>(R.id.textDate)
        textDate?.text = cuadre.createdAt.toString("yyyy/MM/dd HH:mm:ss")

        val textCash = view?.findViewById<TextView>(R.id.textCash)
        textCash?.text = cuadre.cash.toString()

        val textDocuments = view?.findViewById<TextView>(R.id.textDocuments)
        textDocuments?.text = (cuadre.ticketsTotal - cuadre.ticketsLeft).toString()

        val textExpenses = view?.findViewById<TextView>(R.id.textMoneyOff)
        textExpenses?.text = cuadre.expenses.toString()

        val textResult = view?.findViewById<TextView>(R.id.textResult)
        textResult?.text = cuadre.revenue.toString()

        return view
    }

    fun setCuadre(cuadre: Cuadre) {
        this.cuadre = cuadre
    }

}