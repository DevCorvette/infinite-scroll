package ru.devcorvette.infinitescroll.base.logic;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.base.presentation.IBasePresenter;

public class BaseInteractor implements IBaseInteractor {

    protected IBasePresenter presenter;

    @Inject
    public BaseInteractor(IBasePresenter presenter) {
        this.presenter = presenter;
    }
}
