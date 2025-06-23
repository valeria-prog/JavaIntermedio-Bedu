public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.processPayment(new CardPayment(), 1500.00);
        processor.processPayment(new PaypalPayment(), 8500.00);
        processor.processPayment(new CryptoPayment(), 10557.00);
        processor.processPayment(new BankTransferPayment(), 150897.27);
    }
}

