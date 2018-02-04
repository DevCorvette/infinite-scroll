package ru.devcorvette.infinitescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.devcorvette.infinitescroll.pager.presentatation.view.IPagerView;
import ru.devcorvette.infinitescroll.scroll.assembly.ScrollAssembly;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "my_debug_";

    private static MainActivity instance;

    public MainActivity() {
        instance = this;
    }

    /**
     * Создает и отображает Scroll module
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        ScrollAssembly sa = new ScrollAssembly();

        addFragment((Fragment)sa.provideScrollView(), false);
    }

    @Override
    public void onBackPressed() {
        for(Fragment fragment : getSupportFragmentManager().getFragments()){
            if (fragment instanceof IPagerView) {
                ((IPagerView) fragment).onBackPressed();
                break;
            }
        }

        super.onBackPressed();
    }

    /**
     * Присоединяет фрагмент.
     * @param isBackStack если true, добавляет в back stack.
     */
    public void addFragment(Fragment fragment, boolean isBackStack){

        FragmentTransaction tx = MainActivity
                .getInstance()
                .getSupportFragmentManager()
                .beginTransaction();

        tx.add(R.id.fragmentContent, fragment);

        if(isBackStack) {
            tx.addToBackStack(fragment.toString());
        }
        tx.commit();
    }

    public static MainActivity getInstance(){
        return instance;
    }

}