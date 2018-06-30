package com.padcmyanmar.charles_keith__app_assignment_mma.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.charles_keith__app_assignment_mma.R;
import com.padcmyanmar.charles_keith__app_assignment_mma.data.model.NewProductModel;
import com.padcmyanmar.charles_keith__app_assignment_mma.data.vos.NewProductVO;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewProductsDetailActivity extends BaseActivity {

    @BindView(R.id.tv_detail_title)
    TextView tvTitle;

    @BindView(R.id.iv_detail_img)
    ImageView ivProductImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this, this);

        String newProductsId = getIntent().getStringExtra("newProductsId" );

        Log.d("NewProductsDetail", "newProductsId :" + newProductsId);

        NewProductVO newProducts = NewProductModel.getObjInstance().getNewProductById(newProductsId);
        
        bindData(newProducts);

    }

    private void bindData(NewProductVO newProducts) {
        tvTitle.setText(newProducts.getProductTitle());

        Glide.with(ivProductImg.getContext())
                .load(newProducts.getProductImage())
                .into(ivProductImg);
    }
}
