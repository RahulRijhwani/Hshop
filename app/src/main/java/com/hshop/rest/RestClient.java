package com.hshop.rest;

import com.hshop.models.AllCancel;
import com.hshop.models.AllCart;
import com.hshop.models.AllContactCheck;
import com.hshop.models.AllHome;
import com.hshop.models.AllOrder;
import com.hshop.models.AllSearch;
import com.hshop.models.CategoryDetails;
import com.hshop.models.ProductDetails;
import com.hshop.models.ProductName;
import com.hshop.models.RegisterUser;
import com.hshop.models.SubCategoryDetails;
import com.hshop.models.UserActiveInfo;
import com.hshop.models.UserAddCart;
import com.hshop.models.UserAddProductCart;
import com.hshop.models.UserChangePassword;
import com.hshop.models.UserChangeProfile;
import com.hshop.models.UserCheckout;
import com.hshop.models.UserForgotPassword;
import com.hshop.models.UserLogin;
import com.hshop.models.UserMinusProductCart;
import com.hshop.models.UserProfile;
import com.hshop.models.UserSuggestion;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;


/**
 * Created by Nilay Patel on 16/11/2017.
 * patelnilay20@gmail.com
 * 9978224432
 */
public class RestClient {

    private static GitApiInterface gitApiInterface;

    private static String baseUrl = "http://hshop.co.in";

    public static GitApiInterface getClient() {
        if (gitApiInterface == null) {

            OkHttpClient okClient = new OkHttpClient();
            okClient.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response response = chain.proceed(chain.request());
                    return response;
                }
            });

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverter(String.class, new ToStringConverter())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            gitApiInterface = client.create(GitApiInterface.class);
        }
        return gitApiInterface;
    }

    public interface GitApiInterface {

        // @Headers("User-Agent: Retrofit2.0")

        @FormUrlEncoded
        @POST("API/fetch_home.php")
        Call<AllHome> getAllHome(@Field("mem_string") String mem_string,@Field("user_id") String user_id);

        @FormUrlEncoded
        @POST("API/fetch_search.php")
        Call<AllSearch> getallsearch(@Field("mem_string") String mem_string, @Field("user_id") String user_id, @Field("edt_serach_text") String edt_serach_text);


        @FormUrlEncoded
        @POST("/API/user_check_already_contact.php")
        Call<AllContactCheck> getAllContactCheck(@Field("mem_string") String mem_string, @Field("mem_contact") String contact, @Field("mem_otp") String finalotp);


        @FormUrlEncoded
        @POST("API/user_register_add.php")
        Call<RegisterUser> getRegister(@Field("deviceid") String deviceid, @Field("mem_string") String mem_string, @Field("mem_gender") String mem_gender1, @Field("mem_fname") String mem_fname1, @Field("mem_lname") String mem_lname1, @Field("mem_contact") String mem_contact1, @Field("mem_email") String mem_email1, @Field("mem_password") String mem_password1, @Field("mem_houseno") String mem_houseno1,
                                       @Field("mem_society") String mem_society1, @Field("mem_locality") String mem_locality1, @Field("mem_pincode") String mem_pincode1);

        @FormUrlEncoded
        @POST("API/user_forgotpassword.php")
        Call<UserForgotPassword> getForgotpassword(@Field("mem_string") String mem_string, @Field("mem_contact") String mem_contact1);

        @FormUrlEncoded
        @POST("API/user_login.php")
        Call<UserLogin> getUserLogin(@Field("mem_string") String mem_string, @Field("mem_contact") String mem_contact1, @Field("mem_password") String mem_password1,@Field("fcm_token") String fcm_token);

        @FormUrlEncoded
        @POST("API/user_update_password.php")
        Call<UserChangePassword> getUserChangePassword(@Field("mem_string") String mem_string, @Field("mem_id") String mem_id1, @Field("mem_currentpassword") String mem_currentpassword1, @Field("mem_newpassword") String mem_newpassword1);

        @FormUrlEncoded
        @POST("API/user_profile.php")
        Call<UserProfile> getUserProfile(@Field("mem_string") String mem_string, @Field("mem_id") String mem_id1);

        @FormUrlEncoded
        @POST("API/user_update_profile.php")
        Call<UserChangeProfile> getUserChangeProfile( @Field("mem_string") String mem_string, @Field("mem_gender") String mem_gender1, @Field("mem_fname") String mem_fname1, @Field("mem_lname") String mem_lname1, @Field("mem_id") String mem_id1, @Field("mem_email") String mem_email1, @Field("mem_houseno") String mem_houseno1,
                                            @Field("mem_society") String mem_society1, @Field("mem_locality") String mem_locality1, @Field("mem_pincode") String mem_pincode1);

        @FormUrlEncoded
        @POST("API/fetch_category_details.php")
        Call<CategoryDetails> getCategoryDetails(@Field("mem_string") String mem_string, @Field("cat_id") String cat_id1);

        @FormUrlEncoded
        @POST("API/fetch_sub_category_details_final.php")
        Call<SubCategoryDetails> getSubCategoryDetails(@Field("mem_string") String mem_string, @Field("sub_cat_id") String sub_cat_id1, @Field("user_id") String user_id);


        @FormUrlEncoded
        @POST("API/fetch_filter_details_final.php")
        Call<SubCategoryDetails> getFilterDetails(@Field("mem_string") String mem_string, @Field("user_id") String user_id,@Field("filter") String filter);

        @FormUrlEncoded
        @POST("API/fetch_offer_details_final.php")
        Call<SubCategoryDetails> getOfferDetails(@Field("mem_string") String mem_string, @Field("user_id") String user_id,@Field("offer") String offer);

        @FormUrlEncoded
        @POST("API/fetch_product_details.php")
        Call<ProductDetails> getProductDetails(@Field("mem_string") String mem_string,@Field("pro_id") String pro_id1,@Field("user_id") String user_id1,@Field("type") String type);

        @FormUrlEncoded
        @POST("API/user_product_add_cart.php")
        Call<UserAddCart> getUserCartUnitAdd(@Field("mem_string") String mem_string, @Field("user_id") String user_id1, @Field("pro_id") String pro_id1, @Field("tpd_id") String product_unit_id);

        @FormUrlEncoded
        @POST("API/user_product_add_cart.php")
        Call<UserAddCart> getUserCartAdd(@Field("mem_string") String mem_string, @Field("user_id") String user_id1, @Field("pro_id") String pro_id1, @Field("tpd_id") String tpd_id1);


        @FormUrlEncoded
        @POST("API/user_cart_details.php")
        Call<AllCart> getCartDetails(@Field("mem_string") String mem_string, @Field("user_id") String user_id);


        @FormUrlEncoded
        @POST("API/user_cart_add_product.php")
        Call<UserAddProductCart> getUserproductaddcart(@Field("mem_string") String mem_string, @Field("user_id") String user_id1, @Field("pro_id") String pro_id1, @Field("ode_id") String od1e_id, @Field("tpd_id") String tpd_id);

        @FormUrlEncoded
        @POST("API/user_cart_minus_product.php")
        Call<UserMinusProductCart> getUserproductminuscart(@Field("mem_string") String mem_string, @Field("user_id") String user_id1, @Field("pro_id") String pro_id1, @Field("ode_id") String od1e_id, @Field("tpd_id") String tpd_id);

        @FormUrlEncoded
        @POST("API/user_feedback.php")
        Call<UserSuggestion> getUserSuggestion(@Field("mem_string") String mem_string, @Field("use_id") String use_id, @Field("use_message") String use_message);

        @FormUrlEncoded
        @POST("API/user_check_out.php")
        Call<UserCheckout> getUsercheckout(@Field("mem_string") String mem_string, @Field("user_id") String user_id, @Field("ord_houseno") String ord_houseno,@Field("ord_society") String ord_society, @Field("ord_locality") String ord_locality, @Field("ord_pincode") String ord_pincode);

        @FormUrlEncoded
        @POST("API/user_order_details.php")
        Call<AllOrder> getAllorder(@Field("mem_string") String mem_string, @Field("user_id") String user_id);

        @FormUrlEncoded
        @POST("API/user_control_application.php")
        Call<UserActiveInfo> getUseractiveinfo(@Field("mem_string") String mem_string, @Field("mem_id") String user_id);

        @FormUrlEncoded
        @POST("API/user_product_name.php")
        Call<ProductName> getProductname(@Field("mem_string") String mem_string);

        @FormUrlEncoded
        @POST("API/user_order_cancel.php")
        Call<AllCancel> getCancelOrder(@Field("mem_string") String mem_string, @Field("order_id") String order_id);
    }
}
