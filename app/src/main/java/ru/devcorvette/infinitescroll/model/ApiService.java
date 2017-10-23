package ru.devcorvette.infinitescroll.model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.devcorvette.infinitescroll.model.api.FeedRequest;
import ru.devcorvette.infinitescroll.model.api.FeedResponse;

public interface ApiService {
    @POST("Feed")
    Call<FeedResponse> getFeed(@Body FeedRequest feedRequest);
}
