package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    Order order;
    Map<String, String> paymentData;
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        String[] methodList = {"VOUCHER_CODE", "PAYMENT_BY_BANK_TRANSFER"};

        if (Arrays.stream(methodList).noneMatch(item -> item.equals(method))) {
            throw new IllegalArgumentException("Invalid payment method");
        }

        this.method = method;

        if (order == null) {
            throw new IllegalArgumentException("Invalid order value");
        }
        this.order = order;

        this.paymentData = paymentData;
        if (method.equals("VOUCHER_CODE")) {
            this.status = verifyVoucherCode();
        }
        else if (method.equals("PAYMENT_BY_BANK_TRANSFER")) {
            this.status = verifyPaymentByBankTransfer();
        }

        if (! PaymentMethod.contains(method)) {
            throw new IllegalArgumentException("Invalid method");
        }
        this.method = method;

        this.order = order;
        this.paymentData = paymentData;

        if (status == null) {
            updateStatus();
        }
    }

    private String verifyVoucherCode() {
        String voucherCode = this.paymentData.get("voucherCode");
        if (voucherCode == null) {
            return "REJECTED";
        }

        if (voucherCode.length() != 16) {
            return "REJECTED";
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return "REJECTED";
        }

        int numCount = 0;
        for (char character: voucherCode.toCharArray()) {
            if (Character.isDigit(character)) {
                numCount += 1;
            }
        }
        if (numCount != 8) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    private String verifyPaymentByBankTransfer() {
        String bankName = this.paymentData.get("bankName");
        String referenceCode = this.paymentData.get("referenceCode");

        if (bankName == null || bankName.isEmpty()) {
            return "REJECTED";
        }

        if (referenceCode == null || referenceCode.isEmpty()) {
            return "REJECTED";
        }

        return "SUCCESS";
    }

    public void updateStatus() {
        if (this.method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            if (! this.paymentData.containsKey("voucherCode")) {
                throw new IllegalArgumentException("Invalid payment data for current method");
            }
            this.status = verifyVoucherCode();
        }
        else if (this.method.equals(PaymentMethod.PAYMENT_BY_BANK_TRANSFER.getValue())) {
            if (! this.paymentData.containsKey("bankName") ||
                    ! this.paymentData.containsKey("referenceCode")) {
                throw new IllegalArgumentException("Invalid payment data for current method");
            }
            this.status = verifyPaymentByBankTransfer();
        }
    }
}