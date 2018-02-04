package ru.devcorvette.infinitescroll.pager.presentatation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.R;

public class Page extends Fragment {
    private static final String TAG = MainActivity.TAG + Page.class.getSimpleName();

    private PageRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    /**
     * Создает отображение страницы - recycler view.
     * Присоединяет adapter and layout manager.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemViewCacheSize(20);

        if (BuildConfig.DEBUG) Log.d(TAG, "on create view of page #" + this);

        return view;
    }

    public void setAdapter(PageRecyclerAdapter adapter) {
        this.adapter = adapter;
    }
}
