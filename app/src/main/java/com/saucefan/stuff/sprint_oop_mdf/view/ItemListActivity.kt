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
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.AoeRepository.makeAoeList
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ApiInterface
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.random.Random



class ItemListActivity : AppCompatActivity(), ItemDetailFragment.FragmentFavoriteListener {




    override fun flipFavorite(item: AoeTypes) {
        Toast.makeText(this, AoeRepository.swapArrayFave(item), Toast.LENGTH_LONG).show()
    }


    private var twoPane: Boolean = false


    override fun onResume() {
        super.onResume()

        item_list.adapter?.notifyDataSetChanged()?: Toast.makeText(this,"recycle issue onresume itemlist", Toast.LENGTH_SHORT)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val handler = Handler(Looper.getMainLooper())
        setContentView(R.layout.activity_item_list)
        title = "Aoe oop sprint challenge"
        var manager:RecyclerView?= item_list
        manager?.adapter = SimpleItemRecyclerViewAdapter(this, twoPane)
        makeAoeList(2,this)

        handler.postDelayed({
            item_list.adapter?.notifyDataSetChanged() ?: Toast.makeText(this,"recycle issue oncreate itemlist", Toast.LENGTH_SHORT).show()
        }, 3000)



        fab.setOnClickListener { view ->
            makeAoeList(6, this)
            //wait a few seconds and try to refresh recycleview
            handler.postDelayed({
                item_list.adapter?.notifyDataSetChanged() ?: Toast.makeText(this,"recycle issue oncreate itemlist", Toast.LENGTH_SHORT).show()
            }, 3000)
        }

        if (item_detail_container != null) {
            twoPane = true
        }




    }



}

