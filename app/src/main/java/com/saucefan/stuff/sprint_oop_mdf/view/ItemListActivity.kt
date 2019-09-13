package com.saucefan.stuff.sprint_oop_mdf.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.saucefan.stuff.sprint_oop_mdf.R
import com.saucefan.stuff.sprint_oop_mdf.model.*
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.AoeRepository
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.AoeRepository.AoeArrayList
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ApiInterface
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random



class ItemListActivity : AppCompatActivity(), ItemDetailFragment.FragmentFavoriteListener {


    fun makeAoeList(number: Int) {
        var apiInterface = ApiInterface.Factory.create()
        for (i in 1 until number) {
            apiInterface.getCiv(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Civlizations> {
                    override fun onFailure(call: Call<Civlizations>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(this@ItemListActivity, response, Toast.LENGTH_SHORT).show()

                    }

                    override fun onResponse(
                        call: Call<Civlizations>,
                        response: Response<Civlizations>
                    ) {
                        val newciv: Civlizations? = response.body()
                        if (newciv != null) {
                            AoeArrayList.add(newciv)
                        }


                    }

                })
        }


        for (i in 1 until number) {
            apiInterface.getUnit(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Units> {
                    override fun onFailure(call: Call<Units>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(this@ItemListActivity, response, Toast.LENGTH_SHORT)
                            .show()

                    }

                    override fun onResponse(
                        call: Call<Units>,
                        response: Response<Units>
                    ) {
                        val newunit: Units? = response.body()
                        if (newunit != null) {
                            AoeArrayList.add(newunit)
                        }


                    }

                })
        }

        for (i in 1 until number) {
            apiInterface.getTech(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Technology> {
                    override fun onFailure(call: Call<Technology>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(this@ItemListActivity, response, Toast.LENGTH_SHORT)
                            .show()

                    }

                    override fun onResponse(
                        call: Call<Technology>,
                        response: Response<Technology>
                    ) {
                        val newtech: Technology? = response.body()
                        if (newtech != null) {
                            AoeArrayList.add(newtech)
                        }


                    }

                })
        }

        for (i in 1 until number) {
            apiInterface.getStructure(Random.nextInt(1, 15).toString())
                .enqueue(object : Callback<Structures> {
                    override fun onFailure(call: Call<Structures>, t: Throwable) {
                        t.printStackTrace()
                        val response = "faliure; ${t.message}"
                        Toast.makeText(this@ItemListActivity, response, Toast.LENGTH_SHORT)
                            .show()

                    }

                    override fun onResponse(
                        call: Call<Structures>,
                        response: Response<Structures>
                    ) {
                        val newstruc: Structures? = response.body()
                        if (newstruc != null) {
                            AoeArrayList.add(newstruc)
                        }


                    }

                })
        setupRecyclerView(item_list)
        }

    }


    override fun flipFavorite(item: AoeTypes) {
        Toast.makeText(this, "isFavorite: ${item.name} ${AoeRepository.swapArrayFave(item)}", Toast.LENGTH_LONG).show()

    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */

    private var twoPane: Boolean = false
    /*override fun onResumeFragments() {
        recyclerView.adapter?.notifyDataSetChanged() ?: Toast.makeText(this,"recycleview issues, onresume main activity",Toast.LENGTH_SHORT).show()

        super.onResumeFragments()
    }*/


    override fun onResume() {
        super.onResume()

        item_list.adapter?.notifyDataSetChanged()?: Toast.makeText(this,"recycle issue onresume itemlist", Toast.LENGTH_SHORT)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        title = "Aoe oop sprint challenge"
        var manager:RecyclerView?= item_list
        manager?.adapter = SimpleItemRecyclerViewAdapter(this, twoPane)

        fab.setOnClickListener { view ->
            makeAoeList(6)
            setupRecyclerView(item_list)
            val handler = Handler(Looper.getMainLooper())
            //wait a few seconds and try to refresh recycleview
            handler.postDelayed({
                item_list.adapter?.notifyDataSetChanged() ?: Toast.makeText(this,"recycle issue oncreate itemlist", Toast.LENGTH_SHORT).show()
            }, 3000)
        }

        if (item_detail_container != null) {
            twoPane = true
        }

        //    setupRecyclerView(item_list)



    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {



    }


}

