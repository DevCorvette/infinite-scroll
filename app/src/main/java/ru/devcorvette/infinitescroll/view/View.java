package ru.devcorvette.infinitescroll.view;

import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.devcorvette.infinitescroll.R;
import rx.subjects.PublishSubject;

public class View implements IView {
    private final Activity activity;
    private int pageCount = 0;
    private PublishSubject<Integer> subject;
    private ProgressBar loadingProgressBar;

    public View(Activity activity) {
        this.activity = activity;

        loadingProgressBar = (ProgressBar) activity.findViewById(R.id.progressBar);

    }

    @Override
    public PublishSubject<Integer> getObservable() {
        return subject = PublishSubject.create();
    }

    /**
     * Запрашивает новые данные.
     */
    private void needData() {
        subject.onNext(pageCount);
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
        if (visible) {
            loadingProgressBar.setVisibility(ProgressBar.VISIBLE);
        } else {
            loadingProgressBar.setVisibility(ProgressBar.INVISIBLE);
        }
    }
}
