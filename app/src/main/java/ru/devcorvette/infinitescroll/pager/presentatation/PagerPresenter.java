package ru.devcorvette.infinitescroll.pager.presentatation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.base.presentation.IBaseRouter;
import ru.devcorvette.infinitescroll.baselist.logic.IBaseListInteractor;
import ru.devcorvette.infinitescroll.baselist.logic.entity.CoverInfo;
import ru.devcorvette.infinitescroll.baselist.logic.entity.Datum;
import ru.devcorvette.infinitescroll.baselist.presentation.BaseListPresenter;
import ru.devcorvette.infinitescroll.pager.logic.entity.PageDatum;
import ru.devcorvette.infinitescroll.pager.presentatation.view.IPagerView;

public class PagerPresenter extends BaseListPresenter implements IPagerPresenter {

    private static final String TAG = MainActivity.TAG + PagerPresenter.class.getSimpleName();

    protected IPagerView view;

    private int startPage;
    private boolean isStartPageShown = false;

    private final List<PageDatum> pagesData = new ArrayList<>();

    public PagerPresenter(IPagerView view, IBaseListInteractor interactor, IBaseRouter router) {
        super(view, interactor, router);
    }

    @Override
    public void updateView(int updateItemCount) {
        int size = pagesData.size();

        for (int i = size; i < size + updateItemCount; i++) {
            pagesData.add(new PageDatum(extractImagesURL(i), extractStrings(i)));
        }

        view.setAvailablePageCount(pagesData.size());

        showStartPage();
    }

    private void showStartPage(){
        if(startPage >= pagesData.size()){

            needUpdateData(pagesData.size());

        } else if(!isStartPageShown){

            view.showStartPage(startPage);
            isStartPageShown = true;
        }
    }

    @Override
    public void needUpdateData(int skip) {
        if(BuildConfig.DEBUG) {
            Log.d(TAG, "need update. skip = " + skip + " pagesData.size() = " + pagesData.size());
        }

        if(skip == pagesData.size()){
            interactor.needUpdateData(skip);
        } else {
            updateView(0);
        }
    }

    @Override
    public PageDatum getPageDatum(int pageNumber) {
        return pagesData.get(pageNumber);
    }

    @Override
    public void showConnectError() {
        router.showConnectError();
    }

    @Override
    public Datum getDatum(int position) {
        return interactor.getDatum(position);
    }

    @Override
    public void setStartPage(int pageNumber) {
        startPage = pageNumber;
        isStartPageShown = false;
    }

    @Override
    public void showScroll(int lastPage) {
        //todo
//        scrollPresenter.scrollToPosition(lastPage);
    }

    private String[] extractImagesURL(int position) {
        CoverInfo[] covers = getDatum(position).getCoverInfo();
        int size = covers.length;
        String[] result = new String[size];

        for (int i = 0; i < size; i++) {
            result[i] = covers[i].getImage();
        }
        return result;
    }

    private String[] extractStrings(int position) {
        Datum datum = getDatum(position);
        return new String[]{
                datum.getId(),
                datum.getType(),
                datum.getTitle(),
                datum.getDescription(),
                datum.getAddress(),
        };
    }
}