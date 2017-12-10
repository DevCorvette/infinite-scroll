package ru.devcorvette.infinitescroll.scroll.presentation;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;

/**
 * Вызывает метод загрузки данных у IScrollPresenter.
 */
public class InfiniteScrollListener extends RecyclerView.OnScrollListener {
    @Inject
    IScrollPresenter presenter;

    private static final String TAG = "my_debug_" + InfiniteScrollListener.class.getSimpleName();

    /**
     * Вызывается только при прокурутки вниз.
     * Когда все элементы стали видимыми - вызывает загрузку данных.
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy < 0) {
            return;
        }

        StaggeredGridLayoutManager staggeredManager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();

        int visibleItemCount = staggeredManager.getChildCount();
        int totalItemCount = staggeredManager.getItemCount();
        int firstVisibleItems = staggeredManager.findFirstVisibleItemPositions(null)[0];

        if ((visibleItemCount + firstVisibleItems) >= totalItemCount) {
            presenter.needData();
        }
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "onScroll visibleItemCount == "
                            + visibleItemCount
                            + " totalItemCount == "
                            + totalItemCount
                            + " firstVisibleItems == "
                            + firstVisibleItems);
        }
    }

    /**
     * Если отображать нечего - вызывает загрузку данных.
     */
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (recyclerView.getLayoutManager().getItemCount() == 0) {
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "item count == 0. Call need data.");
            }
            presenter.needData();
        }
    }
}
