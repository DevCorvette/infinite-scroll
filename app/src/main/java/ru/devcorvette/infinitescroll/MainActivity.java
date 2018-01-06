package ru.devcorvette.infinitescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.assembly.Assembly;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;
import ru.devcorvette.infinitescroll.pager.presentatation.view.IPagerView;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;

public class MainActivity extends AppCompatActivity implements Router {

    @Inject IScrollView scrollView;

    @Inject IScrollPresenter scrollPresenter;

    @Inject IPagerView pagerView;

    @Inject IPagerPresenter pagerPresenter;

    /**
     * Initial assembly application and show main scroll fragment.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Assembly(this).assemble();

        setContentView(R.layout.main);

        showScroll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Присоединяет фрагмент.
     * @param isBackStack если true, добавляет в back stack.
     */
    private void addFragment(Fragment fragment, boolean isBackStack){
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.add(R.id.fragmentContent, fragment);

        if(isBackStack) {
            tx.addToBackStack(fragment.toString());
        }
        tx.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        scrollPresenter.scrollToPosition(pagerPresenter.getLastCurrentPosition());
    }

    /**
     * Show toast message.
     */
    private void showMessage(String message){
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void showScroll() {
        addFragment((Fragment) scrollView, false);
    }

    @Override
    public void showPage(int pageNumber) {
        addFragment((Fragment) pagerView, true);
        pagerPresenter.setStartPage(pageNumber);
    }

    @Override
    public void showConnectError() {
        showMessage(getString(R.string.connect_error));
    }
}