package subsystem_;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 * This {@code HttpConnector} class setup connection
 * in our ECO BIKE Software.
 *
 * @author nhom2
 *
 */
@Slf4j
public class HttpConnector {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10000, TimeUnit.MILLISECONDS)
            .readTimeout(10000,TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true)
            .build() ;

    public String sendPatch(String url , String body) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE), body);
            Request request = new Request.Builder().url(url)
                    .patch(requestBody).build();
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            return response.body().string();
        } catch (Exception e) {
            System.out.println("Lỗi! (method: sendPatch)");
        }
        return null;
    }
}
