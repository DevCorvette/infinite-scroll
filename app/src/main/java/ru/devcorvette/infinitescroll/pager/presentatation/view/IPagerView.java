package ru.devcorvette.infinitescroll.pager.presentatation.view;

import ru.devcorvette.infinitescroll.pager.logic.entity.PageDatum;

public interface IPagerView {
    /**
     * запрашивает данные страницы по номеру.
     */
    PageDatum getPageData(int pageNumber);

    /**
     * Устанавливает доступное количество страниц.
     */
    void setAvailablePageCount(int updateCount);

    /**
     * Устанавливает стартовую страницу.
     */
    void showStartPage(int pageNumber);

    /**
     * Запрашивает обновление данных.
     * @param skip количество уже загруженных данных.
     */
    void needUpdate(int skip);
}
