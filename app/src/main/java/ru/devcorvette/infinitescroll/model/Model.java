package ru.devcorvette.infinitescroll.model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.model.api.FeedRequest;
import ru.devcorvette.infinitescroll.model.api.FeedResponse;
import rx.subjects.PublishSubject;

public class Model implements IModel {
    private PublishSubject<FeedResponse> subject;
    private ApiService api = RetroClient.getApiService();

    private static final String TAG = "my_debug_" + Model.class.getSimpleName();

    public Model() {
        subject = PublishSubject.create();
    }

    @Override
    public PublishSubject<FeedResponse> getObservable() {
        return subject;
    }

    @Override
    public void loadData(int skip, int take) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "loadData skip == " + skip + " take == " + take);
        }
        Call<FeedResponse> call = api.getFeed(new FeedRequest(skip, take));

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                if (response.isSuccessful()) {
                    subject.onNext(response.body());
                } else {
                    Log.w(TAG, "Callback response successful is false");
                    subject.onNext(null);
                }
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Log.w(TAG, "Callback on failure == " + t.getMessage());
                subject.onNext(null);
            }
        });
    }
}
