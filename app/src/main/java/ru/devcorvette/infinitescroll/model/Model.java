package ru.devcorvette.infinitescroll.model;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.devcorvette.infinitescroll.model.api.FeedRequest;
import ru.devcorvette.infinitescroll.model.api.FeedResponse;
import rx.subjects.PublishSubject;

public class Model implements IModel {
    private PublishSubject<FeedResponse> subject;

    private int take = 25;

    private ApiService api = RetroClient.getApiService();

    @Override
    public PublishSubject<FeedResponse> getObservable() {
        return subject = PublishSubject.create();
    }

    @Override
    public void loadData(int skip) throws RuntimeException {
        Call<FeedResponse> call = api.getFeed(new FeedRequest(skip, take));

        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                subject.onNext(response.body());
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                throw new RuntimeException(t);
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
