package ru.devcorvette.infinitescroll.scroll.presentation.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.baselist.presentation.view.BaseListAdapter;
import ru.devcorvette.infinitescroll.baselist.presentation.view.BaseListView;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;

public class ScrollView extends BaseListView implements IScrollView {

    protected IScrollPresenter presenter;

    @Inject
    public ScrollView(IScrollPresenter presenter) {
        super(presenter);
    }

    @Override
    public void showPage(int pageNumber){
        presenter.showPage(pageNumber);
    }

    @Override
    public String getImageURL(int position) {
        return presenter.getImageURL(position);
    }

    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
    }

    @Override
    protected BaseListAdapter createAdapter() {
        return new ScrollListAdapter(this);
    }

    @Override
    protected RecyclerView.OnScrollListener createListener() {
        return new InfiniteScrollListener(this);
    }
}
