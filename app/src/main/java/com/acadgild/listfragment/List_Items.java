package com.acadgild.listfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class List_Items extends ListFragment {
    OnListItemSelectedListener mCallback;

    public interface OnListItemSelectedListener {
        /**
         * the listener will get Activated whenever the
         * listview is updated.
         *
         * @param position : the item in the view which is clicked.
         */
        public void onArticleSelected(int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** I will check if the system is coming from a previous state.
         * It is either a screen change or a resuming of an Activity.
         * **/

        /** We are having a differebr list item for devices older than HoneyComb.
         */
        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 :
                android.R.layout.simple_list_item_1;
        // Create a Adaptor for the listview to be set..
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, MainActivity.Headlines));

    }

    @Override
    public void onStart() {
        super.onStart();
        /** When the list is open in tab :: two-pane-layout
         * we have highlight the one which is selected.
         */
        /** getFragmentManager()=> private constructor**/
        if (getFragmentManager().findFragmentById(R.id.article_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        /** The activity that calling the fragment should have the Interface in it*/
        try {

            mCallback = (OnListItemSelectedListener) activity;

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());

        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        mCallback.onArticleSelected(position);
        //two -pane : we have to select the item.
        getListView().setItemChecked(position, true);
    }
}
