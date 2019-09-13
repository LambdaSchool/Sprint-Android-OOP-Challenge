package com.example.aoe.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.example.aoe.Model.AoeAPI
import com.example.aoe.R

import com.example.aoe.viewModel.AOEobject
import com.example.aoe.viewModel.Structure
import com.example.aoe.viewModel.Unit
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_detail.*
import kotlinx.android.synthetic.main.item_list_content.view.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity(), ItemDetailFragment.DetailResponse {
    override fun provideInfoForObject(info: String) {
        Toast.makeText(this, "We got this info from the detail fragment: \n" +
                "$info", Toast.LENGTH_LONG).show()

        item_text.text = "$info"
    }

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    //set up API var
    var AOeobjects = mutableListOf<AOEobject>()
    lateinit var aoeapi: AoeAPI
    private var viewAdapter: SimpleItemRecyclerViewAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)

        AOeobjects = mutableListOf()

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }
        aoeapi = AoeAPI.Factory.create()


        setupRecyclerView(item_list as RecyclerView)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        viewAdapter = SimpleItemRecyclerViewAdapter(this, AOeobjects, twoPane) //swap comes as variable from above
        recyclerView.adapter = viewAdapter

        if (isNetworkConnected()) {
            getData()
        } else {
            Toast.makeText(this@ItemListActivity, getString(R.string.app_name), Toast.LENGTH_LONG)
                .show()
        }
    }
    private fun getData(){
        val structure = mutableListOf(1, 2, 3, 4 , 5, 6, 7 , 8, 9)
        structure.shuffle()
        structure.forEach {

            getStructures(it)
        }

        val units = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        units.shuffle()
        units.forEach {
            getUnits(it)
        }
    }
    fun getStructures(id: Int){
        aoeapi.getStructures(id).enqueue( object : Callback<Structure>{
            override fun onFailure(call: Call<Structure>, t: Throwable) {
            }

            override fun onResponse(call: Call<Structure>, response: Response<Structure>) {
                if (response.isSuccessful){
                    val structure = response.body()
                    structure?.let {
                        it.id = id
                        AOeobjects.add(structure)
                        viewAdapter?.notifyItemChanged(AOeobjects.size -1)
                    }
                }
            }
        })
    }
    fun getUnits(id: Int){
        aoeapi.getUnits(id).enqueue( object : Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful){
                    val structure = response.body()
                    structure?.let {
                        it.id = id
                        AOeobjects.add(structure)
                        viewAdapter?.notifyItemChanged(AOeobjects.size -1)
                    }
                }
            }
        })
    }


    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: MutableList<AOEobject>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private var lastPosition = -1

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as AOEobject
                if (twoPane) {
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable(ItemDetailFragment.ARG_ITEM_ID, item)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra(ItemDetailFragment.ARG_ITEM_ID, item)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = "${item.id}"

            // TODO 2 setting texts attempt
            holder.contentView.text = item.name ?: "unsuccessful attempt to do something 1"

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
                setEnterAnimation(holder.contentView, position)

            }
        }

        override fun getItemCount() = values.size

        private fun setEnterAnimation(viewToAnimate: View, position: Int) {
            if (position > lastPosition) {
                val animation = AnimationUtils.loadAnimation(
                    viewToAnimate.context,
                    android.R.anim.slide_in_left
                )
                viewToAnimate.startAnimation(animation)
                lastPosition = position
            }
        }




        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.name
        }
    }
    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }
}
