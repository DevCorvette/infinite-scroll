package ru.devcorvette.infinitescroll.view;

import rx.Observable;

public interface IView {
    Observable<Integer> getObservable();

    /**
     * Показывает информационное сообщение.
     *
     * @param message message
     */
    void showMessage(String message);

    /**
     * Показывает/скрывает процесс загрузки данных.
     *
     * @param visible visible
     */
    void setProgressVisibility(boolean visible);
}
