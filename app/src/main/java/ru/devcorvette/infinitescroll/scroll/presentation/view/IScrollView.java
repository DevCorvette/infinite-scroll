package ru.devcorvette.infinitescroll.scroll.presentation.view;

public interface IScrollView {
    /**
     * @param updateViewCount количество новых данных доступных для обновления.
     */
    void updateView(int updateViewCount);

    /**
     * Запрос на обновление данных.
     */
    void needUpdate();

    /**
     * Вызвать вызвать модуль отображение страницы.
     */
    void showPage(int pageNumber);

    /**
     * Запрашивает URL изображения.
     */
    String getImageURL(int position);

    /**
     * @return количество видимых элементов.
     */
    int getVisibleItemCount();

    /**
     * Show the progress bar.
     */
    void showProgress();

    /**
     * Hide the progress bar.
     */
    void hideProgress();

    /**
     * Scroll to the specified adapter position.
     */
    void scrollToPosition(int position);
}
