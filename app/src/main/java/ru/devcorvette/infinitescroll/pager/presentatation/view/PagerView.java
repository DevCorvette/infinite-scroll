package ru.devcorvette.infinitescroll.pager.presentatation.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.baselist.presentation.view.BaseListAdapter;
import ru.devcorvette.infinitescroll.baselist.presentation.view.BaseListView;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;

public abstract class PagerView extends BaseListView implements IPagerView {
    private static final String TAG = MainActivity.TAG + PagerView.class.getSimpleName();

    protected IPagerPresenter presenter;

//    private ViewPager viewPager;
//    private PagerFragmentAdapter pagerAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    public PagerView(IPagerPresenter presenter) {
        super(presenter);
    }
    /*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.view_pager, null);
//        viewPager = view.findViewById(R.id.viewPager);
//        pagerAdapter = new PagerFragmentAdapter(this, getChildFragmentManager());
//        viewPager.setAdapter(pagerAdapter);

        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);


        if (BuildConfig.DEBUG) Log.d(TAG, "on create view.");

        needUpdate(0);

        return view;
    }

    @Override
    public PageDatum getPageData(int pageNumber){
        return presenter.getPageDatum(pageNumber);
    }

    @Override
    public void setAvailablePageCount(final int updatedCount) {
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                int oldSize = pagerAdapter.getCount();

                if (oldSize == updatedCount) return;

                if (BuildConfig.DEBUG) Log.d(TAG, "set available count " + updatedCount);

                pagerAdapter.setAvailableCount(updatedCount);
                pagerAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showStartPage(final int pageNumber){
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                if (BuildConfig.DEBUG) Log.d(TAG, "set current page " + pageNumber);

                viewPager.setCurrentItem(pageNumber, false);
            }
        });
    }
*/
    @Override
    public void needUpdate(int skip) {
        if (BuildConfig.DEBUG) Log.d(TAG, "call need update data. Skip = " + skip);

        presenter.needUpdateData(skip);
    }

    @Override
    public void onBackPressed() {
//        presenter.showScroll(viewPager.getCurrentItem());
    }

    //todo refactor
    @Override
    protected RecyclerView.LayoutManager createLayoutManager() {
        return null;
    }

    @Override
    protected BaseListAdapter createAdapter() {
        return null;
    }

    @Override
    protected RecyclerView.OnScrollListener createListener() {
        return null;
    }
}
