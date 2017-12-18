package ru.devcorvette.infinitescroll.base.logic;

import android.widget.ImageView;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;

/**
 * todo
 */
public interface IBaseInteractor {

    /**
     * todo
     */
    Datum getDatum(int itemPosition);

    /**
     * todo
     */
    void putBitmapInView(int itemPosition, int bitmapPosition, ImageView imageView);

    /**
     * todo
     */
    void getBitmapsCount(int itemPosition );
}
