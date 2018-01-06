package ru.devcorvette.infinitescroll.pager.presentatation.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.pager.logic.entity.PageDatum;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;

public class PagerView extends Fragment implements IPagerView {
    private static final String TAG = Router.TAG + PagerView.class.getSimpleName();

    @Inject IPagerPresenter presenter;

    @Inject Router router;

    private ViewPager viewPager;
    private PagerFragmentAdapter pagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.view_pager, null);
        viewPager = view.findViewById(R.id.viewPager);
        pagerAdapter = new PagerFragmentAdapter(this, getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        if (BuildConfig.DEBUG) Log.d(TAG, "on create view.");

        needUpdate(0);

        return view;
    }

    /**
     * Устанавливает номер последней открытой страницы перед закрытием.
     */
    @Override
    public void onDestroyView() {
        presenter.setLastCurrentPosition(viewPager.getCurrentItem());
        super.onDestroyView();
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

    @Override
    public void needUpdate(int skip) {
        if (BuildConfig.DEBUG) Log.d(TAG, "call need update data. Skip = " + skip);

        presenter.needUpdateData(skip);
    }
}
