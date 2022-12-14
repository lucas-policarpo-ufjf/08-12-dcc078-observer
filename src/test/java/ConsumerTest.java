import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsumerTest {

    @Test
    void shouldNotifyOneConsumerWhenTheBookIsBackInStock() {
        Consumer consumer = new Consumer("Lucas", "lucas@email.com");
        Book book = new Book("The end of eternity", "Isaac Asimov", 0);
        consumer.notifyMeWhenBackInStock(book);
        book.addToStock(1);

        assertEquals(
                "Email to:lucas@email.com. Book [name=The end of eternity, author=Isaac Asimov, stockQuantity=0] disponível na biblioteca!",
                consumer.getLastNotificationEmail());
    }

    @Test
    void shouldNotifyMultipleConsumersWhenTheBookIsBackInStock() {
        Consumer lucasConsumer = new Consumer("Lucas", "lucas@email.com");
        Consumer marcoConsumer = new Consumer("Marco", "marco@email.com");
        Book book = new Book("The end of eternity", "Isaac Asimov", 0);

        lucasConsumer.notifyMeWhenBackInStock(book);
        marcoConsumer.notifyMeWhenBackInStock(book);
        book.addToStock(1);

        assertEquals(
                "Email to:lucas@email.com. Book [name=The end of eternity, author=Isaac Asimov, stockQuantity=0] disponível na biblioteca!",
                lucasConsumer.getLastNotificationEmail());
        assertEquals(
                "Email to:marco@email.com. Book [name=The end of eternity, author=Isaac Asimov, stockQuantity=0] disponível na biblioteca!",
                marcoConsumer.getLastNotificationEmail());
    }

    @Test
    void shouldNotNotifyConsumersOutsideTheStockNotificationList() {
        Consumer consumer = new Consumer("Lucas", "lucas@email.com");
        Book book = new Book("The end of eternity", "Isaac Asimov", 0);
        book.addToStock(1);

        assertEquals(null, consumer.getLastNotificationEmail());
    }

    @Test
    void shouldNotifyOnlyTheCorrectConsumers() {
        Consumer consumer1 = new Consumer("Lucas", "lucas@email.com");
        Book book1 = new Book("The end of eternity", "Isaac Asimov", 0);
        consumer1.notifyMeWhenBackInStock(book1);
        book1.addToStock(1);

        Consumer consumer2 = new Consumer("Marco", "marco@email.com");
        Book book2 = new Book("Foundation", "Isaac Asimov", 0);
        consumer2.notifyMeWhenBackInStock(book2);

        assertEquals(
                "Email to:lucas@email.com. Book [name=The end of eternity, author=Isaac Asimov, stockQuantity=0] disponível na biblioteca!",
                consumer1.getLastNotificationEmail());
        assertEquals(null, consumer2.getLastNotificationEmail());
    }

    @Test
    void shouldNotAllowBooksInStockToBeAddedToStockNotificationList() {
        try {
            Consumer consumer = new Consumer("Lucas", "lucas@email.com");
            Book book = new Book("The end of eternity", "Isaac Asimov", 5);
            consumer.notifyMeWhenBackInStock(book);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Livro ja esta disponivel no estoque!", e.getMessage());
        }
    }

    @Test
    void shouldNotifyTheConsumerOnlyOneTime() {
        Consumer consumer = new Consumer("Lucas", "lucas@email.com");
        Book book = new Book("The end of eternity", "Isaac Asimov", 0);
        consumer.notifyMeWhenBackInStock(book);
        book.addToStock(1);

        book.setName("The end of eternity: New edition");
        book.removeFromStock(book.getStockQuantity());
        book.addToStock(10);

        assertEquals(
                "Email to:lucas@email.com. Book [name=The end of eternity, author=Isaac Asimov, stockQuantity=0] disponível na biblioteca!",
                consumer.getLastNotificationEmail());
    }

}