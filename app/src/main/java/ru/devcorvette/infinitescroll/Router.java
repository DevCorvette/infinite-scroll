package ru.devcorvette.infinitescroll;

/**
 * Отвечает за навигацию между модулями.
 */
public interface Router {
    String TAG = "my_debug_";

    /**
     * Перейти на главное окно.
     */
    void showScroll();

    /**
     * Перейти на окно страницы pageNumber.
     */
    void showPage(int pageNumber);

    /**
     * Отображает сообщение об отсутвии соединения.
     */
    void showConnectError();
}
