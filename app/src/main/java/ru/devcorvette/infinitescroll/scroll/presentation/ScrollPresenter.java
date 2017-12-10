package ru.devcorvette.infinitescroll.scroll.presentation;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.scroll.logic.IScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.logic.entity.Datum;

public class ScrollPresenter implements IScrollPresenter {
    private static final String TAG = "my_debug_" + ScrollPresenter.class.getSimpleName();

    private boolean isLoad = false;

    @Inject IScrollInteractor interactor;

    @Inject IScrollView scrollView;

    @Inject Router router;

    @Inject DatumAdapter datumAdapter;

    @Override
    public void needData() {
        if(!isLoad) {
            if(BuildConfig.DEBUG){
                Log.d(TAG, "need data");
            }

            isLoad = true;
            int size = getData().size();
            scrollView.addProgressItem();
            interactor.loadData(size);
        }
    }

    @Override
    public void setData(List<Datum> downloadedData) {
        if(BuildConfig.DEBUG){
            Log.d(TAG, "set data");
        }

        scrollView.setData(downloadedData);
        isLoad = false;
    }

    @Override
    public List<Datum> getData() {
        return datumAdapter.getData();
    }

    @Override
    public void showConnectError() {
        router.showConnectError();
    }

    @Override
    public void showServerError() {
        router.showServerError();
    }
}
