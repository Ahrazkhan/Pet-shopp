package me.iluonu.ahrazkhan;

public class BasketModel {

    String productName,price,img,id;

    public BasketModel( String id,String productName, String price, String img) {
        this.productName = productName;
        this.price = price;
        this.img = img;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
