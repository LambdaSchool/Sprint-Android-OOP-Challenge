package com.saucefan.stuff.sprint_oop_mdf.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.saucefan.stuff.sprint_oop_mdf.R
import com.saucefan.stuff.sprint_oop_mdf.model.AoeTypes
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ArrayListVehicles.AoeArrayList
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.ArrayListVehicles.ITEM_MAP
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

class ItemDetailFragment : Fragment() {






    var item: AoeTypes?= null
    /* ArrayListVehicles.ITEMS
                            ArrayListVehicles.ITEM_MAP
                            ArrayListVehicles.AoeArrayList*/


    interface Favorite {
        fun flipFavorite(item:AoeTypes,context: Context) {
            // so we get here from the map, but the recycler view take the array -- and that just seems crumbly
            //my peronsal toolbox doesn't really know how to handle this well so i'm gonna settle for just handling it at all right now
            fun toastPop(message:String) {
                Toast.makeText(context,"flipfavorites $message", Toast.LENGTH_SHORT).show()
            }
            for (i in 0 until AoeArrayList.size) {
                if (AoeArrayList[i] == item ){
                    if (item.isFavorite){

                        item.isFavorite = false
                        toastPop("$item is now unfavorited -- item. favorite value=${item.isFavorite} (and should be false)")
                        break
                    }else {
                        item.isFavorite = true
                        toastPop("$item is now favorited --item. favorite value=${item.isFavorite} (and should be true)")
                        break
                    }
                }

            }


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

        // Show the dummy content as text in a TextView.
        item?.let {
            rootView.item_detail.text = it.show()

        }


        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
