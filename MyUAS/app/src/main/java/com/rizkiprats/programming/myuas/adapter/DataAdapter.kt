package com.rizkiprats.programming.myuas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizkiprats.programming.myuas.R
import com.rizkiprats.programming.myuas.model.DataModel
import kotlinx.android.synthetic.main.item_list.view.*

class DataAdapter(val data: List<DataModel>?) : RecyclerView.Adapter<DataAdapter.MyViewHolder>() {

    //Override fun didapatkan dari implement member
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {

        //item_list merupakan layout yang kita tambahkan
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)

        return MyViewHolder(view)

    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = data?.get(position)
        holder.setData(item)

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(item: DataModel?) {
            //Pada list kita tidak menampilkan keterangan
            itemView.imageView.setImageResource(R.drawable.ic_baseline_person_24)
            itemView.nama_dosen.text = item?.nama_dosen
            itemView.mata_kuliah.text = item?.mata_kuliah
            itemView.percent_bersemangat.text = item?.presentasi_bersemangat

        }
    }
}