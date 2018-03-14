package ru.devcorvette.infinitescroll.base.presentation;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.base.logic.IBaseInteractor;
import ru.devcorvette.infinitescroll.base.presentation.view.IBaseView;

public class BasePresenter implements IBasePresenter {

    protected IBaseView view;
    protected IBaseInteractor interactor;
    protected IBaseRouter router;

    @Inject
    public BasePresenter(IBaseView view, IBaseInteractor interactor, IBaseRouter router) {
        this.view = view;
        this.interactor = interactor;
        this.router = router;
    }
}
