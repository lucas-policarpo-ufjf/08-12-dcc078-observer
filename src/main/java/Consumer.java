import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Consumer implements Observer {
  private String name;
  private String email;
  private String lastNotificationEmail;

  public Consumer(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public void notifyMeWhenBackInStock(Book book) {
    if (book.getStockQuantity() != 0) {
      throw new IllegalArgumentException("Livro ja esta disponivel no estoque!");
    }
    book.addObserver(this);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLastNotificationEmail() {
    return lastNotificationEmail;
  }

  @Override
  public void update(Observable book, Object obj) {
    this.lastNotificationEmail = "Email to:" + this.email + ". " + book.toString() + " disponível na biblioteca!";
    book.deleteObserver(this);
  }
}
