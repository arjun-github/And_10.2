package com.acadgild.listfragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Single_View extends Fragment {

    final static String ARG_POSITION="position";
    int mCurrentPosition=-1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* If the activity is recreated , any thing : Screen Rotation/ Restore
         */
        if(savedInstanceState!=null){
            mCurrentPosition=savedInstanceState.getInt(ARG_POSITION);
        }
        return inflater.inflate(R.layout.list,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args=getArguments();

        if(args!=null){
            //set the Article here
            // args.get("ProfileId");
            updateArticleView(args.getInt(ARG_POSITION));

        }else if(mCurrentPosition != -1){
            updateArticleView(mCurrentPosition);
        }

    }

    public void updateArticleView(int position){
        TextView article=(TextView) getActivity().findViewById(R.id.article);
        article.setText(MainActivity.Headlines[position]);
        mCurrentPosition=position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(ARG_POSITION,mCurrentPosition);
    }
}