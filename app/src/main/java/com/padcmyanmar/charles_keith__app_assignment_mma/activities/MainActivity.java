package com.padcmyanmar.charles_keith__app_assignment_mma.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.padcmyanmar.charles_keith__app_assignment_mma.R;
import com.padcmyanmar.charles_keith__app_assignment_mma.adapters.NewProductsAdapter;
import com.padcmyanmar.charles_keith__app_assignment_mma.data.model.NewProductModel;
import com.padcmyanmar.charles_keith__app_assignment_mma.data.vos.NewProductVO;
import com.padcmyanmar.charles_keith__app_assignment_mma.delegates.NewProductsDelegate;
import com.padcmyanmar.charles_keith__app_assignment_mma.events.SuccessGetNewProductsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NewProductsDelegate{

    private NewProductsAdapter mNewProductAdapter;

    @BindView(R.id.iv_view1)
    ImageView ivViewOne;

    @BindView(R.id.iv_view2)
    ImageView ivViewTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final RecyclerView rvNewProduct = findViewById(R.id.rv_charlie_view1);
        mNewProductAdapter = new NewProductsAdapter(this);
        rvNewProduct.setAdapter(mNewProductAdapter);
        rvNewProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        ivViewOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvNewProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false));
            }
        });

        ivViewTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvNewProduct.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            }
        });

        NewProductModel.getObjInstance().LoadNewProducts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /*@Override
    public void onTapImage(NewProductVO newProducts){
        Intent intent = new Intent(getApplicationContext(), NewProductsDetailActivity.class);
        intent.putExtra("newProductsId", newProducts.getProductId());
        startActivity(intent);
    }*/

    @Override
    public void onTapImage(NewProductVO newProducts) {
        Intent intent = new Intent(getApplicationContext(), NewProductsDetailActivity.class);
        intent.putExtra("newProductsId", newProducts.getProductId());
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event){
        Log.d("onSuccessGetNewProducts","onSuccessGetNewProducts :" + event.getNewProductsList());
        mNewProductAdapter.setNewProductsList(event.getNewProductsList());
    }
}
