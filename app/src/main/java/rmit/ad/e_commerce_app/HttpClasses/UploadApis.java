package rmit.ad.e_commerce_app.HttpClasses;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadApis {
    @Multipart
    @POST("uploadimage")
    Call<RequestBody> uploadImage(@Part MultipartBody.Part part, @Part("data") RequestBody requestBody);

    @Multipart
    @POST("upload")
    Call<RequestBody> uploadMultiImage(@Part MultipartBody.Part[] multiplePart,
                                       @Part("accessToken") RequestBody accessToken,
                                       @Part("category") RequestBody category,
                                       @Part("title") RequestBody title,
                                       @Part("price") RequestBody price,
                                       @Part("colors") RequestBody colors,
                                       @Part("sizes") RequestBody sizes,
                                       @Part("description") RequestBody description,
                                       @Part("brand") RequestBody brand,
                                       @Part("quantity") RequestBody quantity);

}
