package ru.devcorvette.infinitescroll.base.logic;

import ru.devcorvette.infinitescroll.base.logic.entity.Datum;

/**
 * Получение данных.
 */
public interface IBaseInteractor {

    /**
     * Запрос на новые данные.
     *
     * @param skip столько данных уже есть.
     */
    void needUpdateData(int skip);

    /**
     * @param position позиция в спике MAIN_DATA.
     */
    Datum getDatum(int position);
}
