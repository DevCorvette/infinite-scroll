package ru.devcorvette.infinitescroll.view;

import java.util.List;

import ru.devcorvette.infinitescroll.model.api.Datum;
import rx.Observable;

public interface IView {

    void onCreate();

    Observable<Integer> getObservable();

    /**
     * Вызывает метод загрузки данных.
     */
    void needData();

    /**
     * Показывает информационное сообщение.
     */
    void showMessage(String message);

    /**
     * Показывает/скрывает процесс загрузки данных.
     */
    void setProgressVisibility(boolean visible);

    /**
     * Отображает данные.
     */
    void showData(List<Datum> data);

}
