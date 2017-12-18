package ru.devcorvette.infinitescroll.scroll.logic;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.BaseInteractor;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;

/**
 * todo
 */
public class ScrollInteractor extends BaseInteractor implements IScrollInteractor {
    private static final String TAG = Router.TAG + ScrollInteractor.class.getSimpleName();

    @Inject IScrollPresenter presenter;

    private boolean isLoad = false;

    @Override
    public void needData(int skip) {
        if(!isLoad) {
            isLoad = true;
            int size = getData().size();

            if(BuildConfig.DEBUG) Log.d(TAG, "need data. skip == " + skip + " data.size == " + size);

            if(skip < size){
                update(skip, size - skip);
            } else {
                loadData(skip);
            }
        }
    }

    /**
     * todo
     */
    protected void loadData(int skip){
        presenter.showProgress();
        super.loadData(skip);
    }

    /**
     * todo
     */
    protected void update(int startItem, int countItem){
        presenter.updateView(startItem, countItem);
        isLoad = false;
    }

    /**
     * todo
     */
    @Override
    protected void setData(List<Datum> downloadedData){
        int startItem = getData().size();
        int countItem = downloadedData.size();
        //todo need refactor hear
//        getData().remove(startItem - 1);

        super.setData(downloadedData);
        update(startItem, countItem);
    }

    protected void showServerError(){
        presenter.showServerError();
    }

    protected void showConnectError(){
        presenter.showConnectError();
    }
}
