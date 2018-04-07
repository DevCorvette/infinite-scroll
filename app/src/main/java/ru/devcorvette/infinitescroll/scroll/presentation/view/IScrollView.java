package ru.devcorvette.infinitescroll.scroll.presentation.view;

import ru.devcorvette.infinitescroll.baselist.presentation.view.IBaseListView;

public interface IScrollView extends IBaseListView {
    /**
     * Вызвать вызвать модуль отображение страницы.
     */
    void showPage(int pageNumber);

    /**
     * Запрашивает URL изображения.
     */
    String getImageURL(int position);
}
