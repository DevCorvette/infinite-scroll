package ru.devcorvette.infinitescroll.scroll.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.scroll.logic.entity.Datum;
import ru.devcorvette.infinitescroll.scroll.logic.entity.FeedResponse;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import rx.Subscription;
import rx.functions.Action1;

public class ScrollInteractor implements IScrollInteractor {

    private static final String TAG = "my_debug_" + ScrollInteractor.class.getSimpleName();

    @Inject IScrollPresenter presenter;

    @Inject DataService dataService;

    private int take = 9;

    private Subscription dataSubscription;

    private void subscribe(){
        dataSubscription = dataService.getObservable().subscribe(new Action1<FeedResponse>() {
            @Override
            public void call(FeedResponse feedResponse) {
                presenter.setData(extractionData(feedResponse));
            }
        });
    }

    @Override
    public void loadData(int skip) {
        if(BuildConfig.DEBUG){
            Log.d(TAG, "load data");
        }
        if (dataSubscription == null){
            subscribe();
        }
        dataService.loadData(skip, take);
    }

    /**
     * Извлекает из feedResponse Datum[] и преобразует в List.
     *
     * @return result с данными, если данных нет, то result пустой.
     */
    private List<Datum> extractionData(FeedResponse feedResponse) {
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
