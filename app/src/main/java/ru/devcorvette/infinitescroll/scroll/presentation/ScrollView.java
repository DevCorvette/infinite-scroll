package ru.devcorvette.infinitescroll.scroll.presentation;

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

import java.util.List;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.scroll.logic.entity.Datum;

public class ScrollView extends Fragment implements IScrollView {

    private static final String TAG = "my_debug_" + ScrollView.class.getSimpleName();

    @Inject DatumAdapter adapter;

    @Inject InfiniteScrollListener scrollListener;

    @Inject IScrollPresenter presenter;

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(BuildConfig.DEBUG){
            Log.d(TAG, "on create view");
        }

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

        if(BuildConfig.DEBUG){
            Log.d(TAG, "on activity created");
        }

        presenter.needData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setData(final List<Datum> downloadedData) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(BuildConfig.DEBUG){
                    Log.d(TAG, "setData");
                }

                List<Datum> data = adapter.getData();

                int progressItem = data.size() - 1;
                int downloadedCount = downloadedData.size();

                data.remove(progressItem);
                data.addAll(downloadedData);

                if(downloadedCount == 0){
                    adapter.notifyItemRemoved(progressItem);
                } else {
                    adapter.notifyItemRangeChanged(progressItem, downloadedCount);
                }
            }
        });
    }

    @Override
    public void addProgressItem() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(BuildConfig.DEBUG){
                    Log.d(TAG, "show progress");
                }

                List<Datum> data = adapter.getData();
                data.add(null);
                adapter.notifyItemInserted(data.size() - 1);
            }
        });
    }
}
