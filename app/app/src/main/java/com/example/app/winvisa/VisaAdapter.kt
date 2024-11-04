package com.example.app.winvisa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R

class VisaAdapter(private val visaList: MutableList<Visa>, var context: Context) : RecyclerView.Adapter<VisaAdapter.VisaViewHolder>() {

    class VisaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCountry: TextView = itemView.findViewById(R.id.tvCountry)
        val tvVisaType: TextView = itemView.findViewById(R.id.tvVisaType)
        val tvIssueDate: TextView = itemView.findViewById(R.id.tvIssueDate)
        val tvExpiryDate: TextView = itemView.findViewById(R.id.tvExpiryDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.visa_item, parent, false)
        return VisaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VisaViewHolder, position: Int) {
        val visa = visaList[position]
        holder.tvCountry.text = visa.country
        holder.tvVisaType.text = "Тип визы: ${visa.visaType}"
        holder.tvIssueDate.text = "Дата начала: ${visa.issueDate}"
        holder.tvExpiryDate.text = "Дата окончания: ${visa.expiryDate}"
    }

    override fun getItemCount() = visaList.size

    fun removeVisa(position: Int) {
        visaList.removeAt(position)
        notifyItemRemoved(position)
    }

    // Метод для восстановления визы
    fun restoreVisa(position: Int, visa: Visa) {
        visaList.add(position, visa)
        notifyItemInserted(position)
    }
}
