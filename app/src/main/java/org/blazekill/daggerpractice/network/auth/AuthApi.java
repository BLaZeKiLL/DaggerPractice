package org.blazekill.daggerpractice.network.auth;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface AuthApi {

    // Dummy
    @GET
    Flowable<ResponseBody> getFakeStuff();

}
