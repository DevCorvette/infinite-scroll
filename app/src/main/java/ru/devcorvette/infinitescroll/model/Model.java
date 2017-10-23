package ru.devcorvette.infinitescroll.model;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.devcorvette.infinitescroll.model.api.CoverInfo;
import ru.devcorvette.infinitescroll.model.api.Datum;
import ru.devcorvette.infinitescroll.model.api.FeedRequest;
import ru.devcorvette.infinitescroll.model.api.FeedResponse;
import rx.subjects.PublishSubject;

public class Model implements IModel {
    private Activity activity;
    private PublishSubject<List<Bitmap>> subject;

    private int take = 5;

    private ApiService api = RetroClient.getApiService();

    public Model(Activity activity) {
        this.activity = activity;
        subject = PublishSubject.create();
    }

    @Override
    public PublishSubject<List<Bitmap>> getObservable() {
        return subject;
    }

    @Override
    public void loadData(int skip) throws RuntimeException {
        Call<FeedResponse> call = api.getFeed(new FeedRequest(skip, take));

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                subject.onNext(loadBitmaps(response.body()));
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                throw new RuntimeException(t);
            }
        });
    }

    /**
     * В FeedResponse ищет URL изображений и вызывает addBitmapToList.
     *
     * @param feedResponse feedResponse
     * @return list bitmaps
     */
    private List<Bitmap> loadBitmaps(final FeedResponse feedResponse) {
        List<Bitmap> bitmaps = new ArrayList<>();

        Datum[] data = feedResponse.getData();
        for (Datum datum : data) {

            CoverInfo[] coversInfo = datum.getCoverInfo();
            for (CoverInfo info : coversInfo) {
                addBitmapToList(info.getImage(), bitmaps);
            }
        }

        return bitmaps;
    }

    /**
     * Загружает изображение и добавляет его в список.
     *
     * @param imageUrl image URL
     */
    private void addBitmapToList(final String imageUrl, final List<Bitmap> list){
        Picasso.with(activity.getApplicationContext())
                .load(imageUrl)
                .into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                list.add(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
    }

    /**
     * @return take
     */
    public int getTake() {
        return take;
    }

    /**
     * @param take take
     */
    public void setTake(int take) {
        this.take = take;
    }

}
