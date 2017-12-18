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
import android.widget.ImageView;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;

public class ScrollView extends Fragment implements IScrollView {

    private static final String TAG = Router.TAG + ScrollView.class.getSimpleName();

    @Inject IScrollPresenter presenter;

    private DatumAdapter adapter;

    private InfiniteScrollListener scrollListener;

    private RecyclerView recyclerView;

    public ScrollView() {
        adapter = new DatumAdapter(this);
        scrollListener = new InfiniteScrollListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(BuildConfig.DEBUG) Log.d(TAG, "on create view");

        View view = inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.setItemViewCacheSize(100);
        recyclerView.addOnScrollListener(scrollListener);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(BuildConfig.DEBUG) Log.d(TAG, "on activity created");

       needData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void updateView(final int itemStart, final int itemCount) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(BuildConfig.DEBUG) {
                    Log.d(TAG, "updateView. itemStart == " + itemStart + " itemCount ==" + itemCount);
                }

                adapter.setVisibleProgress(false);
                adapter.setItemCount(adapter.getItemCount() + itemCount );

                if(itemCount == 0){
                    adapter.notifyItemRemoved(itemStart);
                } else {
                    adapter.notifyItemRangeChanged(itemStart,itemCount);
                }
            }
        });
    }

    /**
     * todo
     */
    @Override
    public void showProgressItem() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(BuildConfig.DEBUG) Log.d(TAG, "show progress item");

                adapter.setVisibleProgress(true);
                adapter.notifyItemInserted(adapter.getItemCount());
            }
        });
    }

    void needData(){
        presenter.needData(adapter.getItemCount());
    }

    /**
     * todo
     */
    Datum getDatum(int position) {
        return presenter.getDatum(position);
    }

    /**
     * todo
     */
    void putBitmapInView(int itemPosition, int bitmapPosition, ImageView imageView) {
        presenter.putBitmapInView(itemPosition, bitmapPosition, imageView);
    }
}
