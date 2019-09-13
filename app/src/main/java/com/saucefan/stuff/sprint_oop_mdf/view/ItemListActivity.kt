package com.saucefan.stuff.sprint_oop_mdf.view

import android.os.Bundle
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

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */


class ItemListActivity : AppCompatActivity(), ItemDetailFragment.FragmentFavoriteListener {


    fun makeAoeList(number: Int) {
        var apiInterface = ApiInterface.Factory.create()
        for (i in 1 until number) {
            apiInterface.getCiv(Random.nextInt(1, 10).toString())
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
            apiInterface.getUnit(Random.nextInt(1, 10).toString())
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
            apiInterface.getTech(Random.nextInt(1, 10).toString())
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
            apiInterface.getStructure(Random.nextInt(1, 10).toString())
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
        }

    }


    override fun flipFavorite(item: AoeTypes) {

        AoeRepository.swapArrayFave(item)
        Toast.makeText(this, "isFavorite: i)", Toast.LENGTH_LONG).show()

        //   manager.adapter?.notifyDataSetChanged()
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
        setSupportActionBar(toolbar)
        toolbar.title = "Aoe oop sprint challenge"

        makeAoeList(4)
        fab.setOnClickListener { view ->

            setupRecyclerView(item_list)
        }

        if (item_detail_container != null) {
            twoPane = true
        }

        //    setupRecyclerView(item_list)

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        var manager = item_list
        manager.adapter = SimpleItemRecyclerViewAdapter(this, AoeArrayList, twoPane)

    }


}

