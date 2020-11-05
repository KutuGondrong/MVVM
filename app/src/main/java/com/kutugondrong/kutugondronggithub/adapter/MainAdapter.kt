package com.kutugondrong.kutugondronggithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kutugondrong.kutugondronggithub.R
import com.kutugondrong.kutugondronggithub.model.User
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private var users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.userName
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar_url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.ic_launcher_foreground)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>, isAsc: Boolean) {
        users = ArrayList()
        users.addAll(list)
        if (isAsc) {
            sortNameByAsc()
        } else {
            sortNameByDesc()
        }
    }

    fun sortNameByAsc() {
        var list = users as MutableList<User>
        list.sortWith(Comparator { o1: User, o2: User ->
            o1.userName.toLowerCase().compareTo(o2.userName.toLowerCase())
        })
        users = list as ArrayList<User>
        notifyDataSetChanged()
    }

    fun sortNameByDesc() {
        var list = users as MutableList<User>
        list.sortWith(Comparator { o1: User, o2: User ->
            o1.userName.toLowerCase().compareTo(o2.userName.toLowerCase())
        })
        list.reverse()
        users = list as ArrayList<User>
        notifyDataSetChanged()
    }
}