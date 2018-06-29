package com.padcmyanmar.charles_keith__app_assignment_mma.network;

import com.padcmyanmar.charles_keith__app_assignment_mma.events.ApiErrorEvent;
import com.padcmyanmar.charles_keith__app_assignment_mma.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles_keith__app_assignment_mma.network.responses.GetNewProductsResponse;
import com.padcmyanmar.charles_keith__app_assignment_mma.utils.NewProductConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements NewProductsDataAgent{

    private static RetrofitDataAgentImpl sObjInstance;

    private NewProductsApi mTheApi;

    RetrofitDataAgentImpl(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewProductConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(NewProductsApi.class);
    }

    public static RetrofitDataAgentImpl getInstance(){
        if (sObjInstance == null){
            sObjInstance = new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void LoadNewProducts(int page, String accessToken) {

        Call<GetNewProductsResponse> loadNewProductsCall = mTheApi.loadNewProductsList(accessToken, page);
        loadNewProductsCall.enqueue(new Callback<GetNewProductsResponse>() {
            @Override
            public void onResponse(Call<GetNewProductsResponse> call, Response<GetNewProductsResponse> response) {
                GetNewProductsResponse newProductsResponse = response.body();
                if (newProductsResponse != null && newProductsResponse.isResponseOK()){
                    SuccessGetNewProductsEvent event = new SuccessGetNewProductsEvent(newProductsResponse.getMmNewProducts());
                    EventBus.getDefault().post(event);
                }else {
                    if (newProductsResponse == null){
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call");
                        EventBus.getDefault().post(event);
                    }else {
                        ApiErrorEvent event = new ApiErrorEvent(newProductsResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetNewProductsResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
