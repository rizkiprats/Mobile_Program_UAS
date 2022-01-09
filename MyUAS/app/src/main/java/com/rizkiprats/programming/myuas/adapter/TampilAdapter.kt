package com.rizkiprats.programming.myuas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rizkiprats.programming.myuas.R
import com.rizkiprats.programming.myuas.model.RekapModel

class TampilAdapter(val rekapModel: ArrayList<RekapModel>): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list,parent,false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(rekapModel[position])
    }

    override fun getItemCount(): Int {
       return rekapModel.size
    }

}
class PostViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    private val Nama:TextView=itemView.findViewById(R.id.nama_dosen)
    private val Kuliah:TextView=itemView.findViewById(R.id.mata_kuliah)
    private val Semangat:TextView=itemView.findViewById(R.id.percent_bersemangat)
    fun bindView(rekapModel: RekapModel){
        Nama.text=rekapModel.nama_dosen
        Kuliah.text=rekapModel.mata_kuliah
        Semangat.text=rekapModel.presentasi_bersemangat
    }
}


