package kz.mobile.mvvm.users.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.mobile.mvvm.R
import kz.mobile.mvvm.users.domain.models.User
import java.lang.Exception

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var arrayList: MutableList<User> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): UsersAdapter.UsersViewHolder {
        return UsersViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        holder.name.text = arrayList[position].name
        holder.surname.text = arrayList[position].surname
        Log.d("akma",arrayList.get(position).image.toString())
        try {
            holder.image.setImageURI(arrayList.get(position).image);
        } catch (e: Exception) {
            Log.d("akmaError", e.toString())
        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setUsers(list: MutableList<User>) {
        arrayList.clear()
        arrayList = list
    }

    inner class UsersViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameTV)
        val surname: TextView = view.findViewById(R.id.surnameTV)
        val image: ImageView = view.findViewById(R.id.imageView)
    }
}