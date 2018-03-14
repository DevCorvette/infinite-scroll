package ru.devcorvette.infinitescroll.base.presentation.view;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.base.presentation.IBasePresenter;

public class BaseView implements IBaseView {

    protected IBasePresenter presenter;

    @Inject
    public BaseView(IBasePresenter presenter) {
        this.presenter = presenter;
    }
}
