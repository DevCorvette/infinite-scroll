package ru.devcorvette.infinitescroll.scroll.presentation;

import android.widget.ImageView;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;

/**
 * todo
 */
public interface IScrollPresenter{
    /**
     * todo
     */
    void needData(int skip);

    /**
     * todo
     */
    Datum getDatum(int itemPosition);

    /**
     * todo
     */
    void putBitmapInView(int itemPosition, int bitmapPosition, ImageView imageView);

    /**
     * Передает сообщение об отсутвии интрнет соединения.
     */
    void showConnectError();

    /**
     * Передает сообщение об ошибке сервера.
     */
    void showServerError();

    /**
     * todo
     */
    void updateView(int startItem, int countItem);

    /**
     * todo
     */
    void showProgress();
}
