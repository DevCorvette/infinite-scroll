package ru.devcorvette.infinitescroll.base.presentation;

import ru.devcorvette.infinitescroll.base.logic.entity.Datum;

/**
 * Связующее звено мужду interactor, router и view.
 */
public interface IBasePresenter {
    /**
     * Запрашивает интерактор - обновить данные.
     */
    void needUpdateData(int skip);

    /**
     * @param updateItemCount количество обновленных данных.
     */
    void updateView(int updateItemCount);

    /**
     * Передает сообщение об отсутвии соединения.
     */
    void showConnectError();

    /**
     * @param position позиция в списке MAIN_DATA.
     */
    Datum getDatum(int position);
}
