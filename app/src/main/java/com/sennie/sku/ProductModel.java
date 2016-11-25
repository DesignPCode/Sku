package com.sennie.sku;

import com.sennie.skulib.model.BaseSkuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：Sku
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2016/11/24 下午4:17
 * 修改人：N.Sun
 * 修改时间：2016/11/24 下午4:17
 * 修改备注：
 */
public class ProductModel {

    //存储所有录入的库存情况
    private Map<String, BaseSkuModel> productStocks = new HashMap<>();

    //记录规格的种类
    private List<AttributesEntity> attributes = new ArrayList<>();

    public Map<String, BaseSkuModel> getProductStocks() {
        return productStocks;
    }

    public void setProductStocks(Map<String, BaseSkuModel> productStocks) {
        this.productStocks = productStocks;
    }

    public List<AttributesEntity> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesEntity> attributes) {
        this.attributes = attributes;
    }

    public static class AttributesEntity {

        private int id;
        private String name;
        private List<AttributeMembersEntity> attributeMembers = new ArrayList<>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<AttributeMembersEntity> getAttributeMembers() {
            return attributeMembers;
        }

        public void setAttributeMembers(List<AttributeMembersEntity> attributeMembers) {
            this.attributeMembers = attributeMembers;
        }

        public static class AttributeMembersEntity {

            private int attributeGroupId;
            private int attributeMemberId;
            private String name;
            private int status;

            public AttributeMembersEntity(int attributeGroupId, int attributeMemberId, String name) {
                this.attributeGroupId = attributeGroupId;
                this.attributeMemberId = attributeMemberId;
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof AttributeMembersEntity))
                    return false;
                AttributeMembersEntity entity = (AttributeMembersEntity) obj;
                return entity.getName().equals(name) && entity.getAttributeGroupId() == attributeGroupId && entity.getAttributeMemberId() == attributeMemberId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getAttributeGroupId() {
                return attributeGroupId;
            }

            public void setAttributeGroupId(int attributeGroupId) {
                this.attributeGroupId = attributeGroupId;
            }

            public int getAttributeMemberId() {
                return attributeMemberId;
            }

            public void setAttributeMemberId(int attributeMemberId) {
                this.attributeMemberId = attributeMemberId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

        }
    }

}
