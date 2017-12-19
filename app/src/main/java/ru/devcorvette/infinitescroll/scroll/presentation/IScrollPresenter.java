package ru.devcorvette.infinitescroll.scroll.presentation;

import java.util.List;

/**
 * Scroll module presenter.
 */
public interface IScrollPresenter{
    /**
     * Запрос на обновление данных у interactor.
     */
    void needUpdateData(int skip);

    /**
     * Передает новые данные во view.
     */
    void updateView(List<String> urls);

    /**
     * Показывает progress bar.
     */
    void showProgress();

    /**
     * Call show page module.
     */
    void showPage(int page);

    /**
     * Передает сообщение об отсутвии интрнет соединения.
     */
    void showConnectError();

    /**
     * Передает сообщение об ошибке сервера.
     */
    void showServerError();
}
