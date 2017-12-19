package ru.devcorvette.infinitescroll.base.logic;

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
}
