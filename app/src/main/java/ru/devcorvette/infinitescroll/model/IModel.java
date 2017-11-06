package ru.devcorvette.infinitescroll.model;

import ru.devcorvette.infinitescroll.model.api.FeedResponse;
import rx.Observable;

/**
 * Создает Observable и загружает данные.
 */
public interface IModel {
    /**
     * @return observable
     */
    Observable<FeedResponse> getObservable();

    /**
     * Загружает данные и отправляет подписчику.
     */
    void loadData(int skip, int take);
}
