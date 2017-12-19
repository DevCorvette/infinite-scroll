package ru.devcorvette.infinitescroll.scroll.presentation;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.scroll.logic.IScrollInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.view.IScrollView;

import java.util.List;

public class ScrollPresenter implements IScrollPresenter {

    @Inject IScrollView scrollView;

    @Inject IScrollInteractor interactor;

    @Inject Router router;

    @Override
    public void needUpdateData(int skip) {
        interactor.needUpdateData(skip);
    }

    @Override
    public void updateView(List<String> urls) {
        scrollView.updateView(urls);
    }

    @Override
    public void showPage(int page) {
        router.showPage(page);
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
    public void showProgress() {
        scrollView.showProgress();
    }
}
