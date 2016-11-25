package com.sennie.sku;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sennie.sku.adapter.SkuAdapter;
import com.sennie.skulib.Sku;
import com.sennie.skulib.model.BaseSkuModel;

public class MainActivity extends AppCompatActivity {

    private Button btnBottom;

    private ProductModel testData;

    UiData mUiData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnBottom = (Button) findViewById(R.id.btn_bottom);
        mUiData = new UiData();
        // 设置模拟数据
        testData = new ProductModel();
        testData.getProductStocks().put("1;4", new BaseSkuModel(13, 20));
        testData.getProductStocks().put("1;5", new BaseSkuModel(14, 10));
        testData.getProductStocks().put("1;7", new BaseSkuModel(13, 40));
        testData.getProductStocks().put("2;4", new BaseSkuModel(16, 70));
        testData.getProductStocks().put("2;6", new BaseSkuModel(17, 30));
        testData.getProductStocks().put("2;7", new BaseSkuModel(12, 22));
        testData.getProductStocks().put("3;5", new BaseSkuModel(11, 25));
        testData.getProductStocks().put("3;6", new BaseSkuModel(19, 21));
        testData.getProductStocks().put("3;7", new BaseSkuModel(10, 29));
        // 设置对应的品种和规格
        ProductModel.AttributesEntity group01 = new ProductModel.AttributesEntity();
        group01.setName("颜色");
        group01.getAttributeMembers().add(0, new ProductModel.AttributesEntity.AttributeMembersEntity(1, 1, "红色"));
        group01.getAttributeMembers().add(1, new ProductModel.AttributesEntity.AttributeMembersEntity(1, 2, "白色"));
        group01.getAttributeMembers().add(2, new ProductModel.AttributesEntity.AttributeMembersEntity(1, 3, "绿色"));
        testData.getAttributes().add(0, group01);//第一组

        ProductModel.AttributesEntity group02 = new ProductModel.AttributesEntity();
        group02.setName("尺寸");
        group02.getAttributeMembers().add(0, new ProductModel.AttributesEntity.AttributeMembersEntity(2, 4, "80cm"));
        group02.getAttributeMembers().add(1, new ProductModel.AttributesEntity.AttributeMembersEntity(2, 5, "90cm"));
        group02.getAttributeMembers().add(2, new ProductModel.AttributesEntity.AttributeMembersEntity(2, 6, "100cm"));
        group02.getAttributeMembers().add(3, new ProductModel.AttributesEntity.AttributeMembersEntity(2, 7, "110cm"));
        testData.getAttributes().add(1, group02);//第二组

    }

    protected void bottomClick(View view) {
        //显示Sku
        showBottomSheetDialog(testData);
    }

    private void showBottomSheetDialog(ProductModel productModel) {
        if (mUiData.getBottomSheetDialog() == null) {
            mUiData.getSelectedEntities().clear();
            mUiData.getAdapters().clear();
            View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);
            LinearLayout llList = (LinearLayout) view.findViewById(R.id.ll_list);//列表
            //添加list组
            for (int i = 0; i < testData.getAttributes().size(); i++) {
                View viewList = getLayoutInflater().inflate(R.layout.bottom_sheet_group, null);
                TextView tvTitle = (TextView) viewList.findViewById(R.id.tv_title);
                RecyclerView recyclerViewBottom = (RecyclerView) viewList.findViewById(R.id.recycler_bottom);
                SkuAdapter skuAdapter = new SkuAdapter(testData.getAttributes().get(i).getAttributeMembers());
                mUiData.getAdapters().add(skuAdapter);
                int item = 4;//设置列数
                GridLayoutManager gridLayoutManager = new GridLayoutManager(this, item);
                recyclerViewBottom.setLayoutManager(gridLayoutManager);
                recyclerViewBottom.setAdapter(skuAdapter);
                llList.addView(viewList);
            }
            // SKU 计算
            mUiData.setResult(Sku.skuCollection(testData.getProductStocks()));
            for (String key : mUiData.getResult().keySet()) {
                Log.d("SKU Result", "key = " + key + " value = " + mUiData.getResult().get(key));
            }
            //设置点击监听器
            for (SkuAdapter adapter : mUiData.getAdapters()) {
                ItemClickListener listener = new ItemClickListener(mUiData, adapter);
                adapter.setOnClickListener(listener);
            }
            // 初始化按钮
            for (int i = 0; i < mUiData.getAdapters().size(); i++) {
                for (ProductModel.AttributesEntity.AttributeMembersEntity entity : mUiData.getAdapters().get(i).getAttributeMembersEntities()) {
                    if (mUiData.getResult().get(entity.getAttributeMemberId() + "") == null || mUiData.getResult().get(entity.getAttributeMemberId() + "").getStock() <= 0) {
                        entity.setStatus(2);
                    }
                }
            }
            //设置价格
            mUiData.setBottomSheetDialog(new BottomSheetDialog(this));
            mUiData.getBottomSheetDialog().setContentView(view);
            View parent = (View) view.getParent();//获取ParentView
            BottomSheetBehavior behavior = BottomSheetBehavior.from(parent);
            view.measure(0, 0);
            behavior.setPeekHeight(view.getMeasuredHeight());
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) parent.getLayoutParams();
            params.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
            parent.setLayoutParams(params);
            mUiData.getBottomSheetDialog().show();
        } else {
            mUiData.getBottomSheetDialog().show();
        }
    }

}
