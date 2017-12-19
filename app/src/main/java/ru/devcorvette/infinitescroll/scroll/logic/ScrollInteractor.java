package ru.devcorvette.infinitescroll.scroll.logic;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.BaseInteractor;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Преобразует загруженные в BaseInteractor данные в список URL.
 */
public class ScrollInteractor extends BaseInteractor implements IScrollInteractor {
    private static final String TAG = Router.TAG + ScrollInteractor.class.getSimpleName();

    @Inject IScrollPresenter presenter;

    @Override
    protected void showProgress() {
        presenter.showProgress();
    }

    /**
     * Создает список urls.
     */
    @Override
    protected void convertDataForView(int startItem, int countItem) {
        List<String> result = new ArrayList<>();
        for (int i = startItem; i < startItem + countItem; i++) {
            result.add(getDatum(i).getCoverInfo()[0].getImage());
        }
        presenter.updateView(result);
    }

    @Override
    protected void showServerError(){
        presenter.showServerError();
    }

    @Override
    protected void showConnectError(){
        presenter.showConnectError();
    }
}
