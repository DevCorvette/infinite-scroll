package ru.devcorvette.infinitescroll.scroll.presentation;

import ru.devcorvette.infinitescroll.base.presentation.IBasePresenter;

/**
 * Scroll module presenter.
 */
public interface IScrollPresenter extends IBasePresenter{

    /**
     * Извлекает из Datum image URL
     * @param position позиция в списке MAIN_DATA.
     */
    String getImageURL(int position);

    /**
     * Вызывает модуль отображения страницы.
     */
    void showPage(int pageNumber);

    /**
     * Scroll to the specified adapter position.
     */
    void scrollToPosition(int position);
}
