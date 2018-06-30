package com.padcmyanmar.charles_keith__app_assignment_mma.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.padcmyanmar.charles_keith__app_assignment_mma.R;
import com.padcmyanmar.charles_keith__app_assignment_mma.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith__app_assignment_mma.delegates.NewProductsDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewProductViewHolder extends RecyclerView.ViewHolder {

    private NewProductsDelegate mNewProductsDelegate;

    private NewProductVO mNewProducts;

    @BindView(R.id.iv_viewOne)
    ImageView ivNewProductsOne;

    @BindView(R.id.tv_product_title)
    TextView tvProductTitle;

    @BindView(R.id.tv_title)
    TextView tvTitle;


    public NewProductViewHolder(View itemView, NewProductsDelegate newProductsDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mNewProductsDelegate = newProductsDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewProductsDelegate.onTapImage(mNewProducts);
            }
        });
    }

    public void setmNewProductsData(NewProductVO newProducts){

        mNewProducts = newProducts;

        tvProductTitle.setText(newProducts.getProductTitle());
       // tvTitle.setText(newProducts.getProductTitle());

        Glide.with(ivNewProductsOne.getContext())
                .load(newProducts.getProductImage())
                .into(ivNewProductsOne);

    }
}
