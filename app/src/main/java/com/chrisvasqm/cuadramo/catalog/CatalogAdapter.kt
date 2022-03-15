package com.chrisvasqm.cuadramo.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.dialogs.ItemOptionsBottomSheetDialogFragment

class CatalogAdapter(private val cuadres: MutableList<Cuadre>, private val manager: FragmentManager) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

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

        holder.catalogItemConstraintLayout.setOnClickListener { displayOptions(cuadre) }
        holder.textRevenue.text = cuadre.revenue.toString()
        holder.textExpenses.text = cuadre.expenses.toString()
        holder.textTickets.text = cuadre.ticketsSold.toString()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val catalogItemConstraintLayout = view.findViewById<View>(R.id.catalogItemConstraintLayout)
        val textRevenue = view.findViewById<TextView>(R.id.textRevenue)
        val textExpenses = view.findViewById<TextView>(R.id.textExpenses)
        val textTickets = view.findViewById<TextView>(R.id.textTickets)
    }

    private fun displayOptions(cuadre: Cuadre) {
        ItemOptionsBottomSheetDialogFragment()
                .apply {
                    setCuadre(cuadre)
                    setManager(manager)
                }
                .show(manager, TAG)
    }

}
