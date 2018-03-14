package ru.devcorvette.infinitescroll.baselist.presentation.view;

import android.support.v4.app.Fragment;

import ru.devcorvette.infinitescroll.base.presentation.view.IBaseView;

public interface IBaseListView extends IBaseView {

    /**
     * Запрос на обновление данных.
     */
    void needUpdate();

    /**
     * @param updateViewCount количество новых данных доступных для обновления.
     */
    void updateView(int updateViewCount);

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

    /**
     * @return количество видимых элементов.
     */
    int getVisibleItemCount();

    /**
     * todo
     */
    Fragment getFragment();
}
