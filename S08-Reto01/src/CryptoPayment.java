public class CryptoPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("🪙 Procesando pago con criptomonedas por $" + String.format("%,.2f", amount));
    }
}

