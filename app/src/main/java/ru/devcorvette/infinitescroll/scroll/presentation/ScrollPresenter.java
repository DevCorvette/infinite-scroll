package ru.devcorvette.infinitescroll.scroll.presentation;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.base.logic.entity.Datum;
import ru.devcorvette.infinitescroll.scroll.assembly.IScrollAssembly;
import ru.devcorvette.infinitescroll.scroll.logic.IScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;

public class ScrollPresenter implements IScrollPresenter {

    @Inject IScrollView scrollView;

    @Inject IScrollInteractor interactor;

    @Inject IScrollRouter router;

    @Inject IScrollAssembly assembly;

    private boolean needUpdateCurrentPosition = false;
    private int currentPosition;

    @Override
    public void showPage(int page) {
        router.showPager();
        assembly.getPagerPresenter().setStartPage(page);
    }

    @Override
    public String getImageURL(int position) {
        return getDatum(position).getCoverInfo()[0].getImage();
    }

    @Override
    public void updateView(int updateItemCount) {
        scrollView.updateView(updateItemCount);

        if(needUpdateCurrentPosition){
            scrollView.scrollToPosition(currentPosition);
            needUpdateCurrentPosition = false;
        }
    }

    @Override
    public void needUpdateData(int skip) {
        interactor.needUpdateData(skip);
    }

    @Override
    public void scrollToPosition(int position) {
        scrollView.hideProgress();
        int visibleCount = scrollView.getVisibleItemCount();

        if(position >= visibleCount){
            needUpdateCurrentPosition = true;
            currentPosition = position;
            needUpdateData(visibleCount);
        } else{
            scrollView.scrollToPosition(position);
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
