package com.padcmyanmar.charles_keith__app_assignment_mma.network;

import com.padcmyanmar.charles_keith__app_assignment_mma.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles_keith__app_assignment_mma.utils.NewProductConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewProductsApi {

    @FormUrlEncoded
    @POST(NewProductConstants.GET_CHARLES)
    Call<GetNewProductsResponse> loadNewProductsList(
            @Field(NewProductConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(NewProductConstants.PARAM_PAGE) int page );
}
