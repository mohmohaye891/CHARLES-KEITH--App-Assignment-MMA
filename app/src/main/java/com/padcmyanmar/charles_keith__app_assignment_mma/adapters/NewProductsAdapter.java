package com.padcmyanmar.charles_keith__app_assignment_mma.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padcmyanmar.charles_keith__app_assignment_mma.R;
import com.padcmyanmar.charles_keith__app_assignment_mma.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith__app_assignment_mma.delegates.NewProductsDelegate;
import com.padcmyanmar.charles_keith__app_assignment_mma.viewholders.NewProductViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductViewHolder> {

    private NewProductsDelegate mNewProductsDelegate;

    private List<NewProductVO> mNewProducts;

    public NewProductsAdapter(NewProductsDelegate mNewProductsDelegate) {
        this.mNewProductsDelegate = mNewProductsDelegate;
        mNewProducts = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_item_one, parent, false);
        return new NewProductViewHolder(view, mNewProductsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewProductViewHolder holder, int position) {
        holder.setmNewProductsData(mNewProducts.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewProducts.size();
    }

    public void setNewProductsList(List<NewProductVO> newProductsList){
        mNewProducts = newProductsList;
        notifyDataSetChanged();
    }
}
