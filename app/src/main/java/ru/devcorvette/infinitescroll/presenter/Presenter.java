package ru.devcorvette.infinitescroll.presenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;

import java.util.List;

import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.view.IView;
import ru.devcorvette.infinitescroll.model.IModel;
import rx.Subscription;
import rx.functions.Action1;

public class Presenter implements IPresenter {
    private final IView view;
    private final IModel model;
    private final Activity activity;

    private Subscription viewSubscription;
    private Subscription modelSubscription;

    public Presenter(IView view, IModel model, Activity activity) {
        this.view = view;
        this.model = model;
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        view.onCreate();
        viewSubscription = view.getObservable().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                loadData(i);
            }
        });

        modelSubscription = model.getObservable().subscribe(new Action1<List<Bitmap>>() {
            @Override
            public void call(List<Bitmap> list) {
                showData(list);
            }
        });
    }

    @Override
    public void onDestroy() {
        if (viewSubscription != null) {
            viewSubscription.unsubscribe();
        }

        if (modelSubscription != null) {
            modelSubscription.unsubscribe();
        }
    }

    private void showData(List<Bitmap> bitmaps) {
        view.showBitmaps(bitmaps);
    }

    /**
     * Загружает данные,
     * уведомляет пользователя, если данные не загружены.
     * Вызывает отображение прогресса загрузки.
     *
     * @param skip skip
     */
    private void loadData(int skip) {
        if (checkConnection()){
            try {

                view.setProgressVisibility(true);
                model.loadData(skip);
                view.setProgressVisibility(false);

            } catch (RuntimeException e) {
                view.showMessage(e.getMessage());
            }
        }
    }

    /**
     * Проверяет интернет соединение.
     * Уведомляет пользователя, если соединение отсутствует.
     */
    private boolean checkConnection() {
        if (((ConnectivityManager) activity
                .getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null) {
            return true;
        } else {
            view.showMessage(activity.getString(R.string.internet_connection_not_available));
            return false;
        }
    }
}
