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
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;

import java.util.List;

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
        if(BuildConfig.DEBUG) Log.d(TAG, "on create scroll view");

        View view = inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        recyclerView.setItemViewCacheSize(100);
        recyclerView.addOnScrollListener(scrollListener);

        needUpdateData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void updateView(final List<String> urls) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {

                adapter.setVisibleProgress(false);

                int itemStart = adapter.getItemCount();
                int itemCount = urls.size();

                adapter.setUrls(urls);

                if(BuildConfig.DEBUG) {
                    Log.d(TAG, "update view. itemStart == " + itemStart
                            + " itemCount == " + itemCount);
                }

                if(itemCount == 0){
                    adapter.notifyItemRemoved(itemStart);
                } else {
                    adapter.notifyItemRangeChanged(itemStart, itemCount);
                }
            }
        });
    }

    @Override
    public void showProgress() {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                if(BuildConfig.DEBUG) Log.d(TAG, "show progress");

                adapter.setVisibleProgress(true);
                adapter.notifyItemInserted(adapter.getItemCount());
            }
        });
    }

    /**
     * Запрос на новые данные.
     */
    void needUpdateData(){
        presenter.needUpdateData(adapter.getItemCount());
    }

    /**
     * Вызвать отображение модуля страницы.
     */
    void showPage(int page){
        presenter.showPage(page);
    }
}
