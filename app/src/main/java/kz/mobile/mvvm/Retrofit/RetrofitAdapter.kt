package kz.mobile.mvvm.Retrofit

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_activity.view.*
import kz.mobile.mvvm.R
import org.w3c.dom.Text

class RetrofitAdapter : RecyclerView.Adapter<RetrofitAdapter.MyViewHolder>() {

    var items = ArrayList<RecyclerData>()
    private lateinit var tvTitle: TextView
    private lateinit var tvDesc: TextView
    private lateinit var imageThumb: ImageView


    fun setListData(data: ArrayList<RecyclerData>) {
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RetrofitAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)

        return MyViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvTitle = view.tvTitle
        val tvDesc = view.tvDesc
        val imageThumb = view.imageThumb

        fun bind(data: RecyclerData) {
            tvTitle.text = data.name
            if (!TextUtils.isEmpty(data.description)) {
                tvDesc.text = data.description
            } else {
                tvDesc.text = "No Desc available."
            }

            val url = data.owner.avatar_url
            Glide.with(imageThumb)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.default_thumb)
                .error(R.drawable.default_thumb)
                .fallback(R.drawable.default_thumb)
                .into(imageThumb)
        }

    }

}