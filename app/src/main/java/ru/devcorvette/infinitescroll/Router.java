package ru.devcorvette.infinitescroll;

/**
 * Отвечает за навигацию между модулями.
 */
public interface Router {

    /**
     * Перейти на главное окно.
     */
    void showScroll();

    /**
     * Перейти на окно карточки.
     */
    void showCard();

    /**
     * Отображает сообщение об отсутвии соединения.
     */
    void showConnectError();

    /**
     * Отображает сообщение об ошибке сервера.
     */
    void showServerError();
}
