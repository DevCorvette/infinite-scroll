package ru.devcorvette.infinitescroll.base.logic;

import java.util.*;

import android.util.Log;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;
import ru.devcorvette.infinitescroll.base.logic.entity.FeedResponse;
import rx.Subscription;
import rx.functions.Action1;

public abstract class BaseInteractor implements IBaseInteractor {
    private static final String TAG = Router.TAG + BaseInteractor.class.getSimpleName();

    private static final List<Datum> MAIN_DATA = new ArrayList<>();

    private boolean isLoad = false;

    private int take = 9;

    private DataService dataService = new DataService(this);

    private Subscription dataSubscription;

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
                finishUpdate(skip, size - skip);
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

        showProgress();
        dataService.loadData(skip, take);
    }

    /**
     * Завершение обновления.
     */
    protected void finishUpdate(int startItem, int countItem){
        isLoad = false;
        convertDataForView(startItem, countItem);
    }

    /**
     * Добавляет загруженные данные.
     */
    protected void setData(List<Datum> downloadedData){
        if(BuildConfig.DEBUG) {
            Log.d(TAG, "set data. oldData.size == " + MAIN_DATA.size()
                    + " downloadedData.size == " + downloadedData.size());
        }
        int startItem = MAIN_DATA.size();
        int itemCount = downloadedData.size();
        MAIN_DATA.addAll(downloadedData);

        finishUpdate(startItem, itemCount);
    }

    /**
     * @param datumPosition позиция в списке MAIN_DATA.
     */
    protected Datum getDatum(int datumPosition){
        return MAIN_DATA.get(datumPosition);
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
     * Запускает отображение прогресса.
     */
    protected abstract void showProgress();

    /**
     * Преобразованиея данных для дальнейшего отображения.
     */
    protected abstract void convertDataForView(int startItem, int countItem);

    /**
     * Call presenter to report server error.
     */
    protected abstract void showServerError();

    /**
     * Call presenter to report connect error.
     */
    protected abstract void showConnectError();
}