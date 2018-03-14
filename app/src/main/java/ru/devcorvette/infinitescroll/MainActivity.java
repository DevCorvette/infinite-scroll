package ru.devcorvette.infinitescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import ru.devcorvette.infinitescroll.base.assembly.BaseAssembly;
import ru.devcorvette.infinitescroll.base.presentation.BaseRouter;
import ru.devcorvette.infinitescroll.baselist.logic.BaseListInteractor;
import ru.devcorvette.infinitescroll.pager.presentatation.view.IPagerView;
import ru.devcorvette.infinitescroll.scroll.presentation.ScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.ScrollView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "my_debug_";

    /**
     * Создает и отображает Scroll module
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
//        ScrollAssembly sa = new ScrollAssembly();
//        addFragment((Fragment)sa.provideScrollView(), false);

        BaseAssembly<ScrollPresenter, ScrollView, BaseListInteractor, BaseRouter> scrollAssembly
                = new BaseAssembly<>(
                        this,
                        ScrollPresenter.class, ScrollView.class,
                        BaseListInteractor.class,
                        BaseRouter.class);

        addFragment(scrollAssembly.getView().getFragment(), false);
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

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();

        tx.add(R.id.fragmentContent, fragment);

        if(isBackStack) {
            tx.addToBackStack(fragment.toString());
        }
        tx.commit();
    }
}