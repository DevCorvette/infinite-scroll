package ru.devcorvette.infinitescroll.scroll.presentation;

import android.widget.ImageView;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;
import ru.devcorvette.infinitescroll.scroll.logic.IScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;

public class ScrollPresenter implements IScrollPresenter {

    @Inject IScrollView scrollView;

    @Inject IScrollInteractor interactor;

    @Inject Router router;

    @Override
    public void needData(int skip) {
        interactor.needData(skip);
    }

    @Override
    public void putBitmapInView(int itemPosition, int bitmapPosition, ImageView imageView) {
        interactor.putBitmapInView(itemPosition, bitmapPosition, imageView);
    }

    @Override
    public Datum getDatum(int itemPosition) {
        return interactor.getDatum(itemPosition);
    }

    @Override
    public void showConnectError() {
        router.showConnectError();
    }

    @Override
    public void showServerError() {
        router.showServerError();
    }

    @Override
    public void updateView(int startItem, int countItem) {
        scrollView.updateView(startItem, countItem);
    }

    @Override
    public void showProgress() {
        scrollView.showProgressItem();
    }
}
