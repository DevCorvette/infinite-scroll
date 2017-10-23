package ru.devcorvette.infinitescroll.view;

import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Синхронизация прокрутки между двумя ListView.
 */
public class ScrollListener implements AbsListView.OnScrollListener {
    private ListView listViewLeft;
    private ListView listViewRight;
    private IView view;

    public ScrollListener(ListView listViewLeft, ListView listViewRight, IView view) {
        this.listViewLeft = listViewLeft;
        this.listViewRight = listViewRight;
        this.view = view;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    /**
     * Synchronizing scrolling.
     * Distance from the top of the first visible element opposite list:
     * sum_heights(opposite invisible screens) - sum_heights(invisible screens) + distance from top of the first visible child.
     *
     * https://github.com/vladexologija/PinterestListView/blob/master/src/com/vladimir/pinterestlistview/ItemsActivity.java
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        int[] leftViewsHeights = listViewLeft.getDrawableState();
        int[] rightViewsHeights = listViewRight.getDrawableState();

        if (view.getChildAt(0) != null) {
            if (view.equals(listViewLeft)) {
                leftViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();

                int h = 0;
                for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
                    h += rightViewsHeights[i];
                }

                int hi = 0;
                for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
                    hi += leftViewsHeights[i];
                }

                int top = h - hi + view.getChildAt(0).getTop();
                listViewRight.setSelectionFromTop(listViewRight.getFirstVisiblePosition(), top);
            } else if (view.equals(listViewRight)) {
                rightViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();

                int h = 0;
                for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
                    h += leftViewsHeights[i];
                }

                int hi = 0;
                for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
                    hi += rightViewsHeights[i];
                }

                int top = h - hi + view.getChildAt(0).getTop();
                listViewLeft.setSelectionFromTop(listViewLeft.getFirstVisiblePosition(), top);
            }
        } else {
            this.view.needData();
        }
    }

}
