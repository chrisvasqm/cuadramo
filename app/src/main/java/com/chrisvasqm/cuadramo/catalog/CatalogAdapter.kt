package com.chrisvasqm.cuadramo.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.data.models.Cuadre

class CatalogAdapter(private val cuadres: MutableList<Cuadre>) : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

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
        holder.textTickets.text = (cuadre.ticketsTotal - cuadre.ticketsLeft).toString()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textRevenue = view.findViewById<TextView>(R.id.textRevenue)
        val textExpenses = view.findViewById<TextView>(R.id.textExpenses)
        val textTickets = view.findViewById<TextView>(R.id.textTickets)
    }
}