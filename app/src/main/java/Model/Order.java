package Model;

public class Order {

    private String ProductId;
    private String ProductoName;
    private String Cantidad;
    private String Price;
    private String Discount;

    public Order() {

    }

    public Order(String productId, String productoName, String cantidad, String price, String discount) {
        this.ProductId = productId;
        this.ProductoName = productoName;
        this.Cantidad = cantidad;
        this.Price = price;
        this.Discount = discount;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductoName() {
        return ProductoName;
    }

    public void setProductoName(String productoName) {
        ProductoName = productoName;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
