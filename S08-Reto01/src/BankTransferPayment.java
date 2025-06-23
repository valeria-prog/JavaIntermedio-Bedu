public class BankTransferPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("🏦 Procesando pago con transferencia bancaria por $" + String.format("%,.2f", amount));
    }
}

