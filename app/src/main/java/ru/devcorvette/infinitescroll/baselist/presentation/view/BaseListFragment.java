package ru.devcorvette.infinitescroll.baselist.presentation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.R;

public class BaseListFragment extends Fragment {

    private static final String TAG = MainActivity.TAG + BaseListFragment.class.getSimpleName();

    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected BaseListAdapter adapter;
    protected RecyclerView.OnScrollListener listener;
    protected IBaseListView baseListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(listener);
        recyclerView.setItemViewCacheSize(30);
        recyclerView.setHasFixedSize(true);

        if(BuildConfig.DEBUG) Log.d(TAG, "on create view.");

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        baseListView.needUpdate();
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setAdapter(BaseListAdapter adapter) {
        this.adapter = adapter;
    }

    public void setListener(RecyclerView.OnScrollListener listener) {
        this.listener = listener;
    }

    public void setBaseListView(IBaseListView baseListView) {
        this.baseListView = baseListView;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
