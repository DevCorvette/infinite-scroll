package ru.devcorvette.infinitescroll.pager.presentatation;

import ru.devcorvette.infinitescroll.base.presentation.IBasePresenter;
import ru.devcorvette.infinitescroll.pager.logic.entity.PageDatum;

public interface IPagerPresenter extends IBasePresenter {
    /**
     * @return данные страницы.
     */
    PageDatum getPageDatum(int pageNumber);

    void setStartPage(int pageNumber);

    /**
     * @return позиция последней открытой страницы.
     */
    int getLastCurrentPosition();

    void setLastCurrentPosition(int position);
}