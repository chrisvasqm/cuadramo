package com.chrisvasqm.cuadramo.view.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.view.dialogs.ItemOptionsBottomSheetDialogFragment

class CatalogAdapter(
    private val cuadres: MutableList<Cuadre>,
    private val manager: FragmentManager
) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    private val TAG = "CatalogAdapter"

    override fun getItemCount(): Int = cuadres.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.catalog_list_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuadre = cuadres[position]

        holder.textRevenue.text = cuadre.revenue.toString()
        holder.textExpenses.text = cuadre.expenses.toString()
        holder.textTickets.text = cuadre.ticketsSold.toString()

        holder.catalogItemConstraintLayout.setOnClickListener { displayOptions(cuadre) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val catalogItemConstraintLayout: ConstraintLayout =
            view.findViewById(R.id.catalogItemConstraintLayout)
        val textRevenue: TextView = view.findViewById(R.id.textRevenue)
        val textExpenses: TextView = view.findViewById(R.id.textExpenses)
        val textTickets: TextView = view.findViewById(R.id.textTickets)
    }

    private fun displayOptions(cuadre: Cuadre) {
        ItemOptionsBottomSheetDialogFragment(cuadre).show(manager, TAG)
    }

}
