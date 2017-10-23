package ru.devcorvette.infinitescroll.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.devcorvette.infinitescroll.R;
import rx.subjects.PublishSubject;

public class View implements IView {
    private final Activity activity;

    private PublishSubject<Integer> subject;
    private ProgressBar loadingProgressBar;

    private ListView listViewLeft;
    private ListView listViewRight;

    private AbsListView.OnScrollListener scrollListener;
    private android.view.View.OnTouchListener touchListener;

    private List<Bitmap> leftBitmapList = new ArrayList<>();
    private List<Bitmap> rightBitmapList = new ArrayList<>();

    private ArrayAdapter leftAdapter;
    private ArrayAdapter rightAdapter;


    public View(Activity activity) {
        this.activity = activity;

        subject = PublishSubject.create();
    }

    @Override
    public void onCreate(){
        listViewLeft = (ListView) activity.findViewById(R.id.list_view_left);
        listViewRight = (ListView) activity.findViewById(R.id.list_view_right);

        leftAdapter = new BitmapsAdapter(activity, R.layout.image_view, leftBitmapList);
        rightAdapter = new BitmapsAdapter(activity, R.layout.image_view, rightBitmapList);

        // TODO: 22.10.2017 add progress bar
//        loadingProgressBar = (ProgressBar) activity.findViewById(R.id.progressBar);

        scrollListener = new ScrollListener(listViewLeft, listViewRight, this);
        touchListener = new TouchListener(listViewLeft, listViewRight);

        listViewLeft.setOnScrollListener(scrollListener);
        listViewLeft.setOnTouchListener(touchListener);
        listViewLeft.setAdapter(leftAdapter);

        listViewRight.setOnScrollListener(scrollListener);
        listViewRight.setOnTouchListener(touchListener);
        listViewRight.setAdapter(rightAdapter);
    }

    @Override
    public PublishSubject<Integer> getObservable() {
        return subject;
    }

    /**
     * Запрашивает новые данные.
     */
    @Override
    public void needData() {
        // TODO: 22.10.2017 need refactor
        subject.onNext(0);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(activity.getApplicationContext(),
                message,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setProgressVisibility(boolean visible) {
//        if (visible) {
//            loadingProgressBar.setVisibility(ProgressBar.VISIBLE);
//        } else {
//            loadingProgressBar.setVisibility(ProgressBar.INVISIBLE);
//        }
    }

    /**
     *
     */
    @Override
    public void showBitmaps(List<Bitmap> bitmaps){
        for(Bitmap bitmap : bitmaps) {
            if (leftBitmapList.size() <= rightBitmapList.size()) {
                leftBitmapList.add(bitmap);
            } else {
                rightBitmapList.add(bitmap);
            }
        }
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }
}
