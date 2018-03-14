package ru.devcorvette.infinitescroll.baselist.presentation;

import ru.devcorvette.infinitescroll.base.presentation.BasePresenter;
import ru.devcorvette.infinitescroll.base.presentation.IBaseRouter;
import ru.devcorvette.infinitescroll.baselist.logic.IBaseListInteractor;
import ru.devcorvette.infinitescroll.baselist.presentation.view.IBaseListView;

public abstract class BaseListPresenter extends BasePresenter implements IBaseListPresenter {

    protected IBaseListView view;
    protected IBaseListInteractor interactor;

    public BaseListPresenter(IBaseListView view, IBaseListInteractor interactor, IBaseRouter router) {
        super(view, interactor, router);
    }
}
