package bookmall.vo;

public class BookVo {
    private Long no;
    private String title;
    private int price;
    private Long categoryNo;

    public BookVo() {}

    public BookVo(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCategoryNo() {
        return categoryNo;
    }
    public void setCategoryNo(Long categoryNo) {
        this.categoryNo = categoryNo;
    }

    @Override
    public String toString() {
        return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", categoryNo=" + categoryNo + "]";
    }
}
CartVo.java
java
복사
편집
package bookmall.vo;

public class CartVo {
    private Long userNo;
    private Long bookNo;
    private int quantity;

    // 조인해서 가져올 때
    private String bookTitle;

    public CartVo() {}

    public Long getUserNo() {
        return userNo;
    }
    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public Long getBookNo() {
        return bookNo;
    }
    public void setBookNo(Long bookNo) {
        this.bookNo = bookNo;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return "CartVo [userNo=" + userNo + ", bookNo=" + bookNo + ", quantity=" + quantity + ", bookTitle=" + bookTitle + "]";
    }
}