package ru.devcorvette.infinitescroll.scroll.logic;

/**
 * Загружает данные с удаленного адреса.
 */
public interface IScrollInteractor {

    /**
     * Загружает данные.
     * @param skip сколько уже загружено - нужно пропустить
     */
    void loadData(int skip);
}
