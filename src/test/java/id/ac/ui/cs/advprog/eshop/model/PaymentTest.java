package id.ac.ui.cs.advprog.eshop.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

class PaymentTest {
    private List<Product> products;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        this.orders = new ArrayList<>();
        Order order1 = new Order("05e4a650-a54d-4c98-baf3-87d3e8d7e0f7", this.products, 1708560000L, "Atang Ancis", OrderStatus.SUCCESS.getValue());
        Order order2 = new Order("5f80d1c3-1fc1-4b48-8714-7c1b7df1fd5d", this.products, 170856000L, "Atang Buncis", OrderStatus.SUCCESS.getValue());
        this.orders.add(order1);
        this.orders.add(order2);
    }

    // Main Payment
    @Test
    void testDuplicatePayment() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);

        assertSame(this.orders.getFirst(), payment.getOrder());
    }

    @Test
    void testPaymentInvalidPaymentMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", "ANOTHER_PAYMENT", this.orders.getFirst(), paymentData);
        });
    }

    @Test
    void testRejectedPaymentEmptyData() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.VOUCHER_CODE.getValue(), null, paymentData);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), null, paymentData);
        });
    }

    // Voucher Code
    @Test
    void testSuccessValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot16Chars() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP");

        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeWrongPrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ADPRO1234ABC5678");

        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedVoucherCodeNot8CharNumerical() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABCDEFG");

        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.VOUCHER_CODE.getValue(), this.orders.getFirst(), paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    // Payment By Bank Transfer
    @Test
    void testSuccessValidPaymentByBankTransfer() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Mandiri");
        paymentData.put("referenceCode", "008");

        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testRejectedPaymentByBankTransferBankName() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("referenceCode", "008");
        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testRejectedPaymentByBankTransferEmptyReferenceCode() {
        Map<String, String> paymentData = new HashMap<String, String>();
        paymentData.put("bankName", "Mandiri");
        Payment payment = new Payment("5e8aeb69-318f-4ae0-b38d-691f3ebe9307", PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue(), this.orders.getFirst(), paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

}