package com.acadgild.listfragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
        List_Items.OnListItemSelectedListener{

    static String[] Headlines={
            "Layout 1", "Layout 2", "Layout 3", "Layout 4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        //We have to check if its a frame Layout
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState !=null){
                return;
            }
            List_Items firstFragment = new List_Items();//initialising instance of List_Items
            firstFragment.setArguments(getIntent().getExtras());//Setting arguments
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,firstFragment).commit();


        }


    }
    /** This is listener **/
    @Override
    public void onArticleSelected(int position) {


        Single_View fragment=(Single_View)
                getSupportFragmentManager()
                        .findFragmentById(R.id.article_fragment);
        if(fragment != null){
            fragment.updateArticleView(position);


        }else{
            Single_View newFragment=new Single_View();
            Bundle args=new Bundle();
            args.putInt(Single_View.ARG_POSITION,position);
            newFragment.setArguments(args);

            FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container,newFragment)
                    .addToBackStack(null)
                    .commit();

        }

    }
}