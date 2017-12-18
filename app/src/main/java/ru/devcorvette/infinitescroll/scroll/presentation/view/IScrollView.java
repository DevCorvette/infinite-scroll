package ru.devcorvette.infinitescroll.scroll.presentation.view;

/**
 * Отображение главного окна прокрутки.
 */
public interface IScrollView {

    /**
     * todo
     */
    void updateView(int startItem, int countItem);

    /**
     * todo
     */
    void showProgressItem();
}

