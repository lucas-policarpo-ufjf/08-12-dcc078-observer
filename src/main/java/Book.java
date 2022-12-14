import java.util.Observable;

@SuppressWarnings("deprecation")
public class Book extends Observable {
  private String name;
  private String author;
  private Integer stockQuantity;

  public Book(String name, String author, Integer stockQuantity) {
    this.name = name;
    this.author = author;
    this.stockQuantity = stockQuantity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getStockQuantity() {
    return stockQuantity;
  }

  public void addToStock(Integer quantity) {
    if (this.stockQuantity == 0 && quantity > 0) {
      this.setChanged();
      this.notifyObservers();
    }

    this.stockQuantity += quantity;
  }

  public void removeFromStock(Integer quantity) {
    Integer newQuantity = this.stockQuantity - quantity;
    this.stockQuantity = newQuantity < 0 ? 0 : newQuantity;
  }

  @Override
  public String toString() {
    return "Book [name=" + name + ", author=" + author + ", stockQuantity=" + stockQuantity + "]";
  }

}
