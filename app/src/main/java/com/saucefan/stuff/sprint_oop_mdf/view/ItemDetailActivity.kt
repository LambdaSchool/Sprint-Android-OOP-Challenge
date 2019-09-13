package com.saucefan.stuff.sprint_oop_mdf.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.saucefan.stuff.sprint_oop_mdf.R
import com.saucefan.stuff.sprint_oop_mdf.model.AoeTypes
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.AoeRepository.swapArrayFave
import kotlinx.android.synthetic.main.activity_item_detail.*


class ItemDetailActivity : AppCompatActivity(), ItemDetailFragment.FragmentFavoriteListener {
    override fun flipFavorite(item: AoeTypes) {

            swapArrayFave(item)
            Toast.makeText(this,"isFavorite: swapArrayFave(item)", Toast.LENGTH_LONG).show()

    }

    //lateinit var attachedActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ItemDetailFragment().apply {
                arguments = Bundle().apply {
                    var item = intent.getSerializableExtra("detailview")
                    putSerializable("detailview", item)
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }


}
