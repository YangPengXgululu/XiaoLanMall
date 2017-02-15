package com.xiaolan.po;


import java.util.List;

/**
 * Author: fallen
 * Date: 17-2-15
 * Time: 上午9:47
 * Usage:
 */
public class OrderPre {

    private String token;
    private DataBean data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int userId;
        private String name;
        private String address;
        private String phone;
        private String note;
        private List<ItemListBean> itemList;

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserId() {
            return userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public static class ItemListBean {
            private int itemID;
            private int num;

            public int getItemID() {
                return itemID;
            }

            public void setItemID(int itemID) {
                this.itemID = itemID;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }
        }
    }
}
