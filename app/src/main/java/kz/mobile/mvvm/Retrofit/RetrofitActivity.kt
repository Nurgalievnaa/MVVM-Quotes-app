package kz.mobile.mvvm.Retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_retrofit.*
import kz.mobile.mvvm.R
import retrofit2.Call
import retrofit2.Response

class RecyclerViewActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter: RetrofitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)


        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter = RetrofitAdapter()
            adapter = recyclerViewAdapter

            val decoration =
                DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }
    }


    private fun createData() {
        val retroInstance = RetroInstance.getRetroInstance().create(Api::class.java)
        val call = retroInstance.getDataFromAPI("atlanta")
        call.enqueue(object : retrofit2.Callback<RecyclerList> {
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    recyclerViewAdapter.setListData(response.body()?.items!!)
                    recyclerViewAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                Toast.makeText(
                    this@RecyclerViewActivity,
                    "Error in getting data from api.",
                    Toast.LENGTH_LONG
                ).show()
            }
        })


        val viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList> {

            if (it != null) {
                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()

            } else {
                Toast.makeText(
                    this@RecyclerViewActivity,
                    "Error in getting data from api.",
                    Toast.LENGTH_LONG
                ).show()
            }

        })

    }
}