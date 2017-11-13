package ru.devcorvette.infinitescroll.presenter;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.R;
import ru.devcorvette.infinitescroll.model.api.Datum;
import ru.devcorvette.infinitescroll.model.api.FeedResponse;
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

    private boolean isLoad = false;

    private static final String TAG = "my_debug_" + Presenter.class.getSimpleName();

    private int take = 9;

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

        modelSubscription = model.getObservable().subscribe(new Action1<FeedResponse>() {
            @Override
            public void call(FeedResponse feedResponse) {
                showData(feedResponse);
            }
        });

        loadData(0);
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

    /**
     * Загружает данные.
     * Старт процесса отображения загрузки.
     */
    private void loadData(int skip) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "load data skip == " + skip);
        }

        if (!isLoad && checkConnection()) {
            isLoad = true;
            view.setProgressVisible();

            model.loadData(skip, take);
        }
    }

    /**
     * Отображает данные во view.
     * Завершение отображения процесса загрузки.
     */
    private void showData(FeedResponse feedResponse) {
        view.showData(getDataList(feedResponse));
        isLoad = false;
    }

    /**
     * Проверяет интернет соединение.
     * Уведомляет пользователя, если соединение отсутствует.
     */
    private boolean checkConnection() {
        boolean result;

        if (((ConnectivityManager) activity
                .getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null) {
            result = true;
        } else {
            view.showMessage(activity.getString(R.string.internet_connection_not_available));
            result = false;
        }

        if (BuildConfig.DEBUG) {
            Log.d(TAG, "internet connection == " + result);
        }
        return result;
    }

    /**
     * Извлекает из feedResponse Datum[] и преобразует в List.
     *
     * @return result с данными, если данных нет, то result пустой.
     */
    private List<Datum> getDataList(FeedResponse feedResponse) {
        List<Datum> result;
        Datum[] data;

        if (feedResponse == null) {
            result = new ArrayList<>();

        } else if ((data = feedResponse.getData()) == null) {
            result = new ArrayList<>();

        } else {
            result = Arrays.asList(data);
        }
        return result;
    }
}
