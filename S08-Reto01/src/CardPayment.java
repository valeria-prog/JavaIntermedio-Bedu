public class CardPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("💳 Procesando pago con tarjeta por $" + String.format("%,.2f", amount));
    }
}
