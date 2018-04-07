package ru.devcorvette.infinitescroll.baselist.logic;

import ru.devcorvette.infinitescroll.base.logic.IBaseInteractor;
import ru.devcorvette.infinitescroll.baselist.logic.entity.Datum;

/**
 * Получение данных.
 */
public interface IBaseListInteractor extends IBaseInteractor {

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
