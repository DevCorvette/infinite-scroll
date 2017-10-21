package ru.devcorvette.infinitescroll.model;

import android.graphics.Bitmap;

import java.util.List;

import rx.Observable;

/**
 * Создает Observable и загружает данные.
 */
public interface IModel {
    /**
     * @return observable
     */
    Observable<List<Bitmap>> getObservable();

    /**
     * Загружает данные и отправляет подписчику.
     *
     * @param skip skip
     */
    void loadData(int skip) throws RuntimeException;
}
