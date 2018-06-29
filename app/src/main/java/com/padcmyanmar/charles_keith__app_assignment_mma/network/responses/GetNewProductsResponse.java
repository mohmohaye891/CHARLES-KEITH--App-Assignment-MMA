package com.padcmyanmar.charles_keith__app_assignment_mma.network.responses;

import com.google.gson.annotations.SerializedName;
import com.padcmyanmar.charles_keith__app_assignment_mma.data.vos.NewProductVO;

import java.util.List;

public class GetNewProductsResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("newProducts")
    private List<NewProductVO> mmNewProducts;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewProductVO> getMmNewProducts() {
        return mmNewProducts;
    }

    public boolean isResponseOK(){
        return (code == 200 && mmNewProducts != null);
    }
}
