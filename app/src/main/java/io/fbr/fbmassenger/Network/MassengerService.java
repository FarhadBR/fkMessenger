package io.fbr.fbmassenger.Network;

import java.util.List;

import io.fbr.fbmassenger.Model.MessageModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by farhad on 10/31/16.
 */

public interface MassengerService {

    @POST("tweet")
    Call<MessageModel> createMessage(@Body MessageModel messageModel);

    @GET("tweet")
    Call<List<MessageModel>> getMessages();


}
