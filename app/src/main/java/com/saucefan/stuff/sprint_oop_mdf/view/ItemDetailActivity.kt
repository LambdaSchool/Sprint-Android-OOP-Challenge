package com.saucefan.stuff.sprint_oop_mdf.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.saucefan.stuff.sprint_oop_mdf.R
import com.saucefan.stuff.sprint_oop_mdf.model.AoeTypes
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ArrayListVehicles
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ArrayListVehicles.swapArrayFave
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


       /*     var item = ITEM_MAP[intent.getStringExtra(ItemDetailFragment.ARG_ITEM_ID) as String] ?: Structures(1,"fail","fail",500)
            flipFavorite(item, this)

*/
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
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
