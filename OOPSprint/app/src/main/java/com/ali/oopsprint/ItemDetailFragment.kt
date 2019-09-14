package com.ali.oopsprint

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ali.oopsprint.dummy.DummyContent
import com.ali.oopsprint.model.Hierarchy
import com.ali.oopsprint.viewmodelpresenter.HierarchyPresenter
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import kotlinx.android.synthetic.main.item_detail.view.favourite_switch

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: Hierarchy? = null


    private var listener: OnDetailsFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = it.getSerializable(ARG_ITEM_ID) as Hierarchy
                activity?.toolbar_layout?.title = item?.name
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
            rootView.item_detail.text = it.description()

        }
        if(item?.favorite==true){
            rootView.favourite_switch.isChecked=true
        }
        else
            rootView.favourite_switch.isChecked=false

        rootView.favourite_switch.setOnClickListener {
            listener?.onDetailsFragmentInteraction(item)

      if(item!=null)
            if(item?.favorite==false){
                HierarchyPresenter.HierarchyList.hierarchy[item?.index.toString().toInt()].favorite=true
                rootView.favourite_switch.isChecked=true
            }
            else
                HierarchyPresenter.HierarchyList.hierarchy[item?.index.toString().toInt()].favorite=false
            rootView.favourite_switch.isChecked=false

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
    interface OnDetailsFragmentInteractionListener {
        fun onDetailsFragmentInteraction(data: Hierarchy?)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnDetailsFragmentInteractionListener) {
            listener = context
        }
    }

}
