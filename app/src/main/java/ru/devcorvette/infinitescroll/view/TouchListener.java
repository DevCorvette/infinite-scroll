package ru.devcorvette.infinitescroll.view;

import android.view.*;
import android.view.View;
import android.widget.ListView;

public class TouchListener implements View.OnTouchListener {

    private ListView listViewLeft;
    private ListView listViewRight;

    private boolean dispatched = false;

    public TouchListener(ListView listViewLeft, ListView listViewRight) {
        this.listViewLeft = listViewLeft;
        this.listViewRight = listViewRight;
    }

    /**
     * Passing the touch event to the opposite list.
     *
     * https://github.com/vladexologija/PinterestListView/blob/master/src/com/vladimir/pinterestlistview/ItemsActivity.java
     */
    @Override
    public boolean onTouch(android.view.View v, MotionEvent event) {
        if (v.equals(listViewLeft) && !dispatched) {
            dispatched = true;
            listViewRight.dispatchTouchEvent(event);
        } else if (v.equals(listViewRight) && !dispatched) {
            dispatched = true;
            listViewLeft.dispatchTouchEvent(event);
        }

        dispatched = false;
        return false;
    }
}
