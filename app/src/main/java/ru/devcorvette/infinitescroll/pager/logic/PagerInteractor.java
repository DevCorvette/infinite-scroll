package ru.devcorvette.infinitescroll.pager.logic;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.base.logic.BaseInteractor;
import ru.devcorvette.infinitescroll.pager.presentatation.IPagerPresenter;

public class PagerInteractor extends BaseInteractor {

    @Inject IPagerPresenter presenter;

    @Override
    protected void showConnectError() {
        presenter.showConnectError();
    }

    @Override
    protected void updateView(int countItem) {
        presenter.updateView(countItem);
    }
}
