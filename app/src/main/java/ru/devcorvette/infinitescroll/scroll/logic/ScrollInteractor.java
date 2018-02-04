package ru.devcorvette.infinitescroll.scroll.logic;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.base.logic.BaseInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;

public class ScrollInteractor extends BaseInteractor implements IScrollInteractor {

    @Inject IScrollPresenter presenter;

    @Override
    protected void showConnectError() {
        presenter.showConnectError();
    }

    @Override
    protected void updateView(int countItem) {
        presenter.updateView(countItem);
    }
}
