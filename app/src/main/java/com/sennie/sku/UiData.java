package com.sennie.sku;

import android.support.design.widget.BottomSheetDialog;

import com.sennie.sku.adapter.SkuAdapter;
import com.sennie.skulib.model.BaseSkuModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：ZHIZHEN
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2016/11/21 下午5:06
 * 修改人：N.Sun
 * 修改时间：2016/11/21 下午5:06
 * 修改备注：
 */
public class UiData {

    BottomSheetDialog mBottomSheetDialog;

    // 保存多组adapter
    List<SkuAdapter> adapters = new ArrayList<>();

    //存放被选中的按钮对应的数据
    List<ProductModel.AttributesEntity.AttributeMembersEntity> selectedEntities = new ArrayList<>();

    //存放计算结果
    Map<String, BaseSkuModel> result;

    public List<SkuAdapter> getAdapters() {
        return adapters;
    }

    public void setAdapters(List<SkuAdapter> adapters) {
        this.adapters = adapters;
    }

    public Map<String, BaseSkuModel> getResult() {
        return result;
    }

    public void setResult(Map<String, BaseSkuModel> result) {
        this.result = result;
    }

    public BottomSheetDialog getBottomSheetDialog() {
        return mBottomSheetDialog;
    }

    public void setBottomSheetDialog(BottomSheetDialog bottomSheetDialog) {
        mBottomSheetDialog = bottomSheetDialog;
    }

    public List<ProductModel.AttributesEntity.AttributeMembersEntity> getSelectedEntities() {
        return selectedEntities;
    }

    public void setSelectedEntities(List<ProductModel.AttributesEntity.AttributeMembersEntity> selectedEntities) {
        this.selectedEntities = selectedEntities;
    }
}
