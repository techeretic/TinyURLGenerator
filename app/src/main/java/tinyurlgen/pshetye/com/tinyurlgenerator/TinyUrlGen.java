package tinyurlgen.pshetye.com.tinyurlgenerator;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by pshetye on 8/30/15.
 */
public class TinyUrlGen {
    //http://tinyurl.com/api-create.php?url=http://www.google.com/

    public interface S {
        @GET("/")
        void getTinyUrl(@Query("q") String q, Callback<Response> callback);
    }

    private static final String HOST = "https://tinyurl-wrapper.appspot.com/";


    public static final void getTinyUrl(String q, Callback<Response> callback) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(HOST).build();
        S s = restAdapter.create(S.class);
        s.getTinyUrl(q, callback);
    }

}
