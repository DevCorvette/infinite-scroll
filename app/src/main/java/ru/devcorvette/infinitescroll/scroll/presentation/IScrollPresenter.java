package ru.devcorvette.infinitescroll.scroll.presentation;

import java.util.List;

import ru.devcorvette.infinitescroll.scroll.logic.entity.Datum;

/**
 * Связующее звено между IScrollView, IScrollInteractor и Router.
 */
public interface IScrollPresenter {

    /**
     * Запрашивает данные у IScrollInteractor.
     */
    void needData();

    /**
     * Принимает загруженные данные.
     */
    void setData(List<Datum> downloadedData);

    /**
     * return List<Datum>.
     */
    List<Datum> getData();

    /**
     * Передает сообщение об отсутвии интрнет соединения.
     */
    void showConnectError();

    /**
     * Передает сообщение об ошибке сервера.
     */
    void showServerError();
}
