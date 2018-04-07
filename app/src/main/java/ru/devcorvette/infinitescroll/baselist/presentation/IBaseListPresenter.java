package ru.devcorvette.infinitescroll.baselist.presentation;

import ru.devcorvette.infinitescroll.base.presentation.IBasePresenter;
import ru.devcorvette.infinitescroll.baselist.logic.entity.Datum;

public interface IBaseListPresenter extends IBasePresenter {
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
