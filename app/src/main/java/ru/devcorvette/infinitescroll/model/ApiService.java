package ru.devcorvette.infinitescroll.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import ru.devcorvette.infinitescroll.model.api.FeedRequest;
import ru.devcorvette.infinitescroll.model.api.FeedResponse;

public interface ApiService {
    @GET("Feed")
    Call<FeedResponse> getFeed(@Body FeedRequest feedRequest);
}
