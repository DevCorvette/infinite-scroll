package ru.devcorvette.infinitescroll.scroll.presentation.view;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.Router;

/**
 * Вызывает метод загрузки данных.
 */
class InfiniteScrollListener extends RecyclerView.OnScrollListener {
    private IScrollView scrollView;

    public InfiniteScrollListener(ScrollView scrollView) {
        this.scrollView = scrollView;
    }

    private static final String TAG = Router.TAG + InfiniteScrollListener.class.getSimpleName();

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
            scrollView.needUpdate();
        }
//        if (BuildConfig.DEBUG) {
//            Log.d(TAG, "onScroll visibleItemCount == "
//                            + visibleItemCount
//                            + " totalItemCount == "
//                            + totalItemCount
//                            + " firstVisibleItems == "
//                            + firstVisibleItems);
//        }
    }

    /**
     * Если отображать нечего - вызывает загрузку данных.
     */
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (recyclerView.getLayoutManager().getItemCount() == 0) {
            if (BuildConfig.DEBUG) Log.d(TAG, "item count == 0. Call need data.");

            scrollView.needUpdate();
        }
    }
}
