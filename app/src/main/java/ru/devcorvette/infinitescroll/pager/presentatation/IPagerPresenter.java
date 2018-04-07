package ru.devcorvette.infinitescroll.pager.presentatation;

import ru.devcorvette.infinitescroll.baselist.presentation.IBaseListPresenter;
import ru.devcorvette.infinitescroll.pager.logic.entity.PageDatum;

public interface IPagerPresenter extends IBaseListPresenter {
    /**
     * @return данные страницы.
     */
    PageDatum getPageDatum(int pageNumber);

    void setStartPage(int pageNumber);

    void showScroll(int lastPage);
}
