public class PaypalPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("💻 Procesando pago con PayPal por $" + String.format("%,.2f", amount));
    }
}

