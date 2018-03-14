package ru.devcorvette.infinitescroll.baselist.presentation.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.base.presentation.view.BaseView;
import ru.devcorvette.infinitescroll.baselist.presentation.IBaseListPresenter;

public abstract class BaseListView extends BaseView implements IBaseListView {

    private static final String TAG = MainActivity.TAG + BaseListView.class.getSimpleName();

    protected IBaseListPresenter presenter;
    protected BaseListFragment fragment;
    protected RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    protected BaseListAdapter adapter;
    protected RecyclerView.OnScrollListener listener;

    public BaseListView(IBaseListPresenter presenter) {
        super(presenter);

        fragment = new BaseListFragment();
        recyclerView = fragment.getRecyclerView();

        layoutManager = createLayoutManager();
        adapter = createAdapter();
        listener = createListener();

        fragment.setLayoutManager(layoutManager);
        fragment.setAdapter(adapter);
        fragment.setListener(listener);
    }

    @Override
    public void needUpdate(){
        if(!adapter.isVisibleProgress()){
            presenter.needUpdateData(adapter.getItemCount());
            showProgress();
        }
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
    public void scrollToPosition(final int position) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                layoutManager.scrollToPosition(position);

                if(BuildConfig.DEBUG) Log.d(TAG, "set current position " + position);
            }
        });
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
    public Fragment getFragment() {
        return fragment;
    }

    protected abstract RecyclerView.LayoutManager createLayoutManager();

    protected abstract BaseListAdapter createAdapter();

    protected abstract RecyclerView.OnScrollListener createListener();
}
