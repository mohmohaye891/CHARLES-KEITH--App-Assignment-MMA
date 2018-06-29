package com.padcmyanmar.charles_keith__app_assignment_mma.data.model;

import com.padcmyanmar.charles_keith__app_assignment_mma.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith__app_assignment_mma.events.SuccessGetNewProductsEvent;
import com.padcmyanmar.charles_keith__app_assignment_mma.network.NewProductsDataAgent;
import com.padcmyanmar.charles_keith__app_assignment_mma.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class NewProductModel {

    private static NewProductModel objInstance;

    private NewProductsDataAgent mDataAgent;

    private Map<String, NewProductVO> mNewProductsMap;

    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private NewProductModel(){
        mDataAgent = RetrofitDataAgentImpl.getInstance();
        mNewProductsMap = new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static NewProductModel getObjInstance(){
        if (objInstance == null){
            objInstance = new NewProductModel();
        }

        return objInstance;
    }

    public void LoadNewProducts(){
        mDataAgent.LoadNewProducts(1, DUMMY_ACCESS_TOKEN);
    }

    public NewProductVO getNewProductById(String newProductId){
        return mNewProductsMap.get(newProductId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event){
        for (NewProductVO newProduct : event.getNewProductsList()){
            mNewProductsMap.put(newProduct.getProductId(), newProduct);
        }
    }
}
