package com.padcmyanmar.charles_keith__app_assignment_mma.events;

import com.padcmyanmar.charles_keith__app_assignment_mma.data.vos.NewProductVO;

import java.util.List;

public class SuccessGetNewProductsEvent {

    private List<NewProductVO> newProductsList;

    public SuccessGetNewProductsEvent(List<NewProductVO> newProductsList) {
        this.newProductsList = newProductsList;
    }

    public List<NewProductVO> getNewProductsList() {
        return newProductsList;
    }
}
