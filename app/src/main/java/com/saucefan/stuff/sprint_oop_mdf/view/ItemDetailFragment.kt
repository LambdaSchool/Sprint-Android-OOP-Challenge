package com.saucefan.stuff.sprint_oop_mdf.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.saucefan.stuff.sprint_oop_mdf.R
import com.saucefan.stuff.sprint_oop_mdf.model.AoeTypes
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

class ItemDetailFragment : Fragment() {


    var item: AoeTypes?= null
var listener:FragmentFavoriteListener? =null
    /* AoeRepository.ITEMS
                            AoeRepository.ITEM_MAP
                            AoeRepository.AoeArrayList*/

interface onDetailChangeListener {
    fun updateItem(item:AoeTypes)
}
    interface FragmentFavoriteListener {

        fun flipFavorite(item:AoeTypes)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentFavoriteListener){
            listener =context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey("detailview")) {
                item = it.getSerializable("detailview") as AoeTypes
                activity?.toolbar_layout?.title = item?.id.toString()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        item?.let {
            rootView.item_detail.text = it.show()
        }


        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_favorite.setOnClickListener{
            listener?.flipFavorite(item as AoeTypes)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()
        listener=null
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"
    }
}
