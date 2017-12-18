package ru.devcorvette.infinitescroll.base.logic;

import android.graphics.Bitmap;

import java.util.*;

import android.util.Log;
import android.widget.ImageView;

import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.Router;
import ru.devcorvette.infinitescroll.base.logic.entity.Datum;
import ru.devcorvette.infinitescroll.base.logic.entity.FeedResponse;
import rx.Subscription;
import rx.functions.Action1;

public abstract class BaseInteractor implements IBaseInteractor {
    private static final String TAG = Router.TAG + BaseInteractor.class.getSimpleName();

    private static final List<Datum> DATA = new ArrayList<>();
    private static final Map<Datum, Bitmap[]> BITMAPS = new HashMap<>();

    private DataService dataService = new DataService(this);
    private BitmapService bitmapService = new BitmapService(BITMAPS);

    private int take = 9;

    private Subscription dataSubscription;

    private void subscribe(){
        dataSubscription = dataService.getObservable().subscribe(new Action1<FeedResponse>() {
            @Override
            public void call(FeedResponse feedResponse) {
                setData(extractionData(feedResponse));
            }
        });
    }

    /**
     * todo
     */
    protected void loadData(int skip){
        if(dataSubscription == null) subscribe();

        dataService.loadData(skip, take);
    }

    /**
     * todo
     */
    protected void setData(List<Datum> downloadedData){
        if(BuildConfig.DEBUG) {
            Log.d(TAG, "set data. oldData.size == " + DATA.size()
                    + " downloadedData.size == " + downloadedData.size());
        }

        DATA.addAll(downloadedData);
        bitmapService.setBitmaps(downloadedData);
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

    @Override
    public Datum getDatum(int itemPosition) {
        return DATA.get(itemPosition);
    }

    @Override
    public void putBitmapInView(int itemPosition, int bitmapPosition, ImageView imageView){
        bitmapService.putBitmapInView(DATA.get(itemPosition), bitmapPosition, imageView);
    }

    @Override
    public void getBitmapsCount(int itemPosition) {

    }

    protected List<Datum> getData() {
        return DATA;
    }

    protected abstract void showServerError();

    protected abstract void showConnectError();

}