package ru.devcorvette.infinitescroll.scroll.presentation;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.baselist.logic.IBaseListInteractor;
import ru.devcorvette.infinitescroll.baselist.logic.entity.Datum;
import ru.devcorvette.infinitescroll.baselist.presentation.BaseListPresenter;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;

public class ScrollPresenter extends BaseListPresenter implements IScrollPresenter {

    protected IScrollView view;
    protected IScrollRouter router;

    private boolean needUpdateCurrentPosition = false;
    private int currentPosition;

    @Inject
    public ScrollPresenter(IScrollView view, IBaseListInteractor interactor, IScrollRouter router) {
        super(view, interactor, router);
    }

    @Override
    public void showPage(int page) {
        //todo нужно чтоб создать второй модуль
//        router.showPager();
//        assembly.getPagerPresenter().setStartPage(page);
    }

    @Override
    public String getImageURL(int position) {
        return getDatum(position).getCoverInfo()[0].getImage();
    }

    @Override
    public void updateView(int updateItemCount) {
        view.updateView(updateItemCount);

        if(needUpdateCurrentPosition){
            view.scrollToPosition(currentPosition);
            needUpdateCurrentPosition = false;
        }
    }

    @Override
    public void needUpdateData(int skip) {
        interactor.needUpdateData(skip);
    }

    @Override
    public void scrollToPosition(int position) {
        view.hideProgress();
        int visibleCount = view.getVisibleItemCount();

        if(position >= visibleCount){
            needUpdateCurrentPosition = true;
            currentPosition = position;
            needUpdateData(visibleCount);
        } else{
            view.scrollToPosition(position);
        }
    }

    @Override
    public Datum getDatum(int position) {
        return interactor.getDatum(position);
    }

    @Override
    public void showConnectError() {
        router.showConnectError();
    }
}
