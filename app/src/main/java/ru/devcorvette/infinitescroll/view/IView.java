package ru.devcorvette.infinitescroll.view;

import android.graphics.Bitmap;

import java.util.List;

import rx.Observable;

public interface IView {

    void onCreate();

    Observable<Integer> getObservable();

    void needData();

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

    /**
     *
     *
     * @param bitmaps
     */
    void showBitmaps(List<Bitmap> bitmaps);

}
