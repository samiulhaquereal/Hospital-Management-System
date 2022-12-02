package network;

import model.GlobalResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("all")
    Call<GlobalResponse> globalresponse();

}