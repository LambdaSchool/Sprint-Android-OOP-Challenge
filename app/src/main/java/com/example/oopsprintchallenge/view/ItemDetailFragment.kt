package com.example.oopsprintchallenge.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.oopsprintchallenge.R
import com.example.oopsprintchallenge.model.EmpireRepository
import com.example.oopsprintchallenge.viewModel.TempItemList
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListActivity]
 * in two-pane mode (on tablets) or a [ItemDetailActivity]
 * on handsets.
 *
 *
 */

/*

In the ItemDetailFragment, you will create a way to let the attached Activity show a Toast message when the state has changed on the detail.

    Inside your Fragment class, create an interface that has a single method. This method should pass back an API object.
    Make the two Activities implement your Fragment interface. In the interface function for each Activity, show a Toast that displays information about the item that was changed and the Activity that is showing the Toast.
    In your Fragment's onAttach method, store the Activity in a member variable so that you can call it when your UI element is activated (onClickListener or otherwise).
    In your Fragment's onDetach method, release the Activity by setting the member variable to null.

*/
class ItemDetailFragment : Fragment() {
    override fun onAttach(context: Context) {

        super.onAttach(context)

        if(context is OnItemDetailFragmentListener){
            myListener = context

        }
    }

    override fun onDetach() {
        super.onDetach()
        myListener = null
    }

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: EmpireRepository? = null
    private var myListener: OnItemDetailFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = TempItemList.empireMap[it.getString(
                    ARG_ITEM_ID
                )]
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
            rootView.item_detail.text = it.getDetails()
            myListener?.onItemDetailInteraction(it)
        }

        rootView.Button_fav.setOnClickListener {
            /* Step 4 In addition to the attributes provided in the data set, add a Boolean variable that tracks some state -
            choose something that interests you, perhaps isFavorite or isInUse - about the API objects.
            Every type of object should have this state (where is the best place to put it?). You can provide a default value of false.
            Go to your layout files and add a TextView or other UI element to display the description you have created (item_list_content.xml and item_detail.xml are good places).
            Also add UI to display and change the state you have added. You might use a Button, an ImageView, a CheckBox, or something else of your choosing,
            just make sure you can both change the value of the object and display the value after it has changed.*/

            if (item!!.isFavorite){
               rootView.text_view_fav.text = getString(R.string.This_is_Favorited)
            }else{
                rootView.text_view_fav.text = getString(R.string.not_favorited) //This simple logic wasted an amount of my life that I would be embarassed to tell anyone
            }

        }

        return rootView
    }

    interface OnItemDetailFragmentListener{
        fun onItemDetailInteraction(data: EmpireRepository)
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
