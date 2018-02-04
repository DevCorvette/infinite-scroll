package ru.devcorvette.infinitescroll.scroll.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;

public class ScrollView extends Fragment implements IScrollView {

    private static final String TAG = MainActivity.TAG + ScrollView.class.getSimpleName();

    @Inject IScrollPresenter presenter;

    private ScrollRecyclerAdapter adapter;

    private InfiniteScrollListener scrollListener;

    private RecyclerView recyclerView;

    private StaggeredGridLayoutManager layoutManager;

    public ScrollView() {
        adapter = new ScrollRecyclerAdapter(this);
        scrollListener = new InfiniteScrollListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(BuildConfig.DEBUG) Log.d(TAG, "on create scroll view");

        View view = inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemViewCacheSize(100);
        recyclerView.addOnScrollListener(scrollListener);

        needUpdate();

        return view;
    }

    @Override
    public void updateView(final int updateViewCount) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                hideProgress();

                int itemStart = adapter.getItemCount();
                adapter.setVisibleItemCount(itemStart + updateViewCount);

                if(BuildConfig.DEBUG) {
                    Log.d(TAG, "update view. itemStart == " + itemStart
                            + " itemCount == " + updateViewCount);
                }

                if(updateViewCount == 0){
                    adapter.notifyItemRemoved(itemStart);
                } else {
                    adapter.notifyItemRangeChanged(itemStart, updateViewCount);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.setVisibleProgress(true);
                adapter.notifyItemInserted(adapter.getItemCount());

                if(BuildConfig.DEBUG) Log.d(TAG, "show progress");
            }
        });
    }

    @Override
    public void hideProgress() {
        adapter.setVisibleProgress(false);
    }

    @Override
    public void needUpdate(){
        if(!adapter.isVisibleProgress()){
            presenter.needUpdateData(adapter.getItemCount());
            showProgress();
        }
    }

    @Override
    public void showPage(int pageNumber){
        presenter.showPage(pageNumber);
    }

    @Override
    public String getImageURL(int position) {
        return presenter.getImageURL(position);
    }

    /**
     * Не учитывает progress item.
     */
    @Override
    public int getVisibleItemCount() {
        int result = adapter.getItemCount();

        if(adapter.isVisibleProgress()) result--;

        return result;
    }

    @Override
    public void scrollToPosition(final int position) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                layoutManager.scrollToPosition(position);

                if(BuildConfig.DEBUG) Log.d(TAG, "set current position " + position);
            }
        });
    }
}
