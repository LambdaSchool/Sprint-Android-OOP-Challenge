package com.saucefan.stuff.sprint_oop_mdf.view
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.saucefan.stuff.sprint_oop_mdf.R
import com.saucefan.stuff.sprint_oop_mdf.model.AoeTypes
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ArrayListVehicles
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ArrayListVehicles.AoeArrayList
import kotlinx.android.synthetic.main.activity_item_list.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.item_list_content.view.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
 lateinit var manager:RecyclerView

class ItemListActivity : AppCompatActivity(),ItemDetailFragment.FragmentFavoriteListener {
    override fun flipFavorite(item: AoeTypes) {

        ArrayListVehicles.swapArrayFave(item)
        Toast.makeText(this,"isFavorite: swapArrayFave(item)", Toast.LENGTH_LONG).show()
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

        fab.setOnClickListener { view ->

        }

        if (item_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(item_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        var manager =recyclerView
        manager.adapter = SimpleItemRecyclerViewAdapter(this, AoeArrayList, twoPane)

    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: ItemListActivity,
        private val values: List<AoeTypes>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as AoeTypes
                if (twoPane) {
                    val fragment = ItemDetailFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable("detailview", item)
                            putString(ItemDetailFragment.ARG_ITEM_ID, item.id.toString())
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                        putExtra("detailview", item)
                        putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id.toString())
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
            holder.idView.text = item.id.toString()
            holder.contentView.text = item.show()

            if (item.isFavorite){
                holder.contentView.setBackgroundColor(ContextCompat.getColor(holder.contentView.context,R.color.highlight))
            }else holder.contentView.setBackgroundColor(ContextCompat.getColor(holder.contentView.context,R.color.background))
            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)

            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}
