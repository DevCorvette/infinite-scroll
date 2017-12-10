package ru.devcorvette.infinitescroll.scroll.logic;

import android.util.Log;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.devcorvette.infinitescroll.BuildConfig;
import ru.devcorvette.infinitescroll.scroll.logic.entity.FeedRequest;
import ru.devcorvette.infinitescroll.scroll.logic.entity.FeedResponse;
import ru.devcorvette.infinitescroll.scroll.presentation.IScrollPresenter;
import rx.subjects.PublishSubject;

/**
 * Загружает данные используя Retrofit, отдает их подписчику.
 */
public class DataService {
    private static final String ROOT_URL = "http://109.111.162.236:8083/api/v2/";
    private static final String TAG = "my_debug_" + DataService.class.getSimpleName();

    private PublishSubject<FeedResponse> subject;
    private ApiService api = getApiService();

    @Inject IScrollPresenter presenter;

    public DataService(){
        subject = PublishSubject.create();
    }

    public PublishSubject<FeedResponse> getObservable() {
        return subject;
    }

    /**
     * @param skip сколько уже загружено - нужно пропустить
     * @param take сколько нужно загрузить
     */
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

            /**
             * Сообщает презентеру об ошибке.
             * Подписчику пердает null.
             */
            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                Log.w(TAG, "Callback on failure == " + t.getMessage());

                if(t instanceof ConnectException){
                    presenter.showConnectError();
                } else if (t instanceof SocketTimeoutException){
                    presenter.showServerError();
                }

                subject.onNext(null);
            }
        });
    }

    /**
     * Retrofit гененирует класс реализующий interface ApiService.
     */
    private ApiService getApiService() {
        return  new Retrofit
                .Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
    }

    interface ApiService {
        @POST("Feed")
        Call<FeedResponse> getFeed(@Body FeedRequest feedRequest);
    }
}
