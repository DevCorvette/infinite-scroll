package ru.devcorvette.infinitescroll.scroll.presentation;

import java.util.List;

import ru.devcorvette.infinitescroll.scroll.logic.entity.Datum;

/**
 * Отображения главного окна прокрутки.
 */
public interface IScrollView {

    /**
     * Добавляет загруженные данные и обновляет отображение.
     */
    void setData(List<Datum> downloadedData);

    /**
     * Добавляет progress item и обновляет отображение
     */
    void addProgressItem();
}
