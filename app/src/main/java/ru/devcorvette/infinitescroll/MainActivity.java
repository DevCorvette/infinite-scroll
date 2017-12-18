package ru.devcorvette.infinitescroll;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.assembly.Assembly;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;

public class MainActivity extends AppCompatActivity implements Router {

    @Inject IScrollView scrollView;

    @Inject IScrollPresenter scrollPresenter;

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
     * Add fragment to the back stack.
     */
    private void addBackStack(Fragment fragment){
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.add(R.id.fragmentContent, fragment);
        tx.addToBackStack(fragment.toString());
        tx.commit();
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
        addBackStack((Fragment) scrollView);
    }

    @Override
    public void showPage(int pageNumber) {

    }

    @Override
    public void showConnectError() {
        showMessage(getString(R.string.connect_error));
    }

    @Override
    public void showServerError() {
        showMessage(getString(R.string.server_error));
    }
}
