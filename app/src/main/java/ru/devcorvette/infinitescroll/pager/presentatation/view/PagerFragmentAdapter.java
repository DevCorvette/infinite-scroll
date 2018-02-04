package ru.devcorvette.infinitescroll.pager.presentatation.view;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.pager.logic.entity.PageDatum;

class PagerFragmentAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = MainActivity.TAG + PagerFragmentAdapter.class.getSimpleName();

    private IPagerView pagerView;
    private int availableCount = 0;

    PagerFragmentAdapter(PagerView pagerView, FragmentManager fm) {
        super(fm);
        this.pagerView = pagerView;
    }

    /**
     * Создает страницу и адаптер.
     * Передает адаптеру данные для отображения.
     */
    @Override
    public Fragment getItem(int position) {
        if(position == availableCount - 1){
            if (BuildConfig.DEBUG) Log.d(TAG, "position " + position + " = available count - 1");

            pagerView.needUpdate(availableCount);
        }
        Page page = new Page();
        PageDatum datum = pagerView.getPageData(position);

        page.setAdapter(new PageRecyclerAdapter(datum.getImagesURL(), datum.getStrings()));

        if (BuildConfig.DEBUG) Log.d(TAG, "create page #" + position);

        return page;
    }

    @Override
    public int getCount() {
        return availableCount;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
//        do nothing.
//        Помогает избежать NullPointerException при пересоздании адаптера.
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
}
