package ru.devcorvette.infinitescroll.scroll.presentation.view;

import java.util.List;

/**
 * Scroll module view.
 */
public interface IScrollView {
    /**
     * Добавляет новые данные для отображения.
     */
    void updateView(List<String> urls);

    /**
     * Отображает progress bar.
     */
    void showProgress();
}

