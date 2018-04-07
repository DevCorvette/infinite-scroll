package ru.devcorvette.infinitescroll.baselist.logic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.MainActivity;
import ru.devcorvette.infinitescroll.base.logic.BaseInteractor;
import ru.devcorvette.infinitescroll.base.presentation.IBasePresenter;
import ru.devcorvette.infinitescroll.baselist.logic.entity.Datum;
import ru.devcorvette.infinitescroll.baselist.logic.entity.FeedResponse;
import ru.devcorvette.infinitescroll.baselist.presentation.IBaseListPresenter;
import rx.Subscription;
import rx.functions.Action1;

public abstract class BaseListInteractor extends BaseInteractor implements IBaseListInteractor {

    private static final String TAG = MainActivity.TAG + BaseListInteractor.class.getSimpleName();

    private static final List<Datum> MAIN_DATA = new ArrayList<>();
    private boolean isLoad = false;
    private int take = 9;

    private DataService dataService = new DataService(this);
    private Subscription dataSubscription;
    protected IBaseListPresenter presenter;

    public BaseListInteractor(IBasePresenter presenter) {
        super(presenter);
    }

    /**
     * Подписаться на получение данных.
     */
    private void subscribe(){
        dataSubscription = dataService.getObservable().subscribe(new Action1<FeedResponse>() {
            @Override
            public void call(FeedResponse feedResponse) {
                setData(extractionData(feedResponse));
            }
        });
    }

    @Override
    public void needUpdateData(int skip) {
        if(!isLoad) {
            isLoad = true;
            int size = MAIN_DATA.size();

            if(BuildConfig.DEBUG) Log.d(TAG, "need data. skip == " + skip + " data.size == " + size);

            if(skip < size){
                if(BuildConfig.DEBUG) Log.d(TAG, "data do not need loaded");

                finishUpdate(size - skip);
            } else {
                loadData(skip);
            }
        }
    }

    /**
     * Загружает данные через DataService.
     */
    protected void loadData(int skip){
        if(dataSubscription == null) subscribe();

        dataService.loadData(skip, take);

        if(BuildConfig.DEBUG) Log.d(TAG, "load data");
    }

    /**
     * @param datumPosition позиция в списке MAIN_DATA.
     */
    public Datum getDatum(int datumPosition){
        return MAIN_DATA.get(datumPosition);
    }

    /**
     * Завершение обновления.
     */
    protected void finishUpdate(int countItem){
        if(BuildConfig.DEBUG) Log.d(TAG, "finish update. Count of updated item " +  countItem);

        isLoad = false;
        updateView(countItem);
    }

    /**
     * Добавляет загруженные данные.
     */
    protected void setData(List<Datum> downloadedData){
        if(BuildConfig.DEBUG) {
            Log.d(TAG, "set data. oldData.size == " + MAIN_DATA.size()
                    + " downloadedData.size == " + downloadedData.size());
        }
        int itemCount = downloadedData.size();
        MAIN_DATA.addAll(downloadedData);

        finishUpdate(itemCount);

        if(BuildConfig.DEBUG) Log.d(TAG, "set data");
    }

    /**
     * Извлекает из feedResponse Datum[] и преобразует в List.
     *
     * @return result с данными, если данных нет, то result пустой.
     */
    protected List<Datum> extractionData(FeedResponse feedResponse) {
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

    /**
     * Call presenter to report connect error.
     */
    protected abstract void showConnectError();

    /**
     * Сообщить отображению об обновленных данных.
     */
    protected abstract void updateView(int countItem);
}