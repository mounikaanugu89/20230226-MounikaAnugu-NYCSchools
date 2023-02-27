package com.example.demomvvmflow.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demomvvmflow.R
import com.example.demomvvmflow.data.model.user.response.NYChighSchools
import com.example.demomvvmflow.ui.view.DetailActivity

class CustomAdapter(private val mList: List<NYChighSchools>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_schools_list, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = mList[position].school_name ?: ""
        holder.itemView.setOnClickListener {
            it.context.startActivity(Intent(it.context, DetailActivity::class.java).apply {
                putExtra("DBN", mList[position].dbn)
                putExtra("schoolName", mList[position].school_name)
            })
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}
