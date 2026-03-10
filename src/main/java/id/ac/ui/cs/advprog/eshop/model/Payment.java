package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;
    private Order order;

    public Payment(String id, String method, Map<String, String> paymentData, Order order) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;

        if ("VOUCHER_CODE".equals(method)) {
            this.status = validateVoucherCode(paymentData.get("voucherCode")) ? "SUCCESS" : "REJECTED";
        } else if ("BANK_TRANSFER".equals(method)) {
            this.status = validateBankTransfer(paymentData) ? "SUCCESS" : "REJECTED";
        } else {
            this.status = "REJECTED";
        }
    }

    public Payment(String id, String method, Map<String, String> paymentData, Order order, String status) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.order = order;
        this.setStatus(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Helper

    private boolean validateVoucherCode(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            return false;
        }
        int numCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                numCount++;
            }
        }
        return numCount == 8;
    }

    private boolean validateBankTransfer(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        return bankName != null && !bankName.trim().isEmpty() &&
                referenceCode != null && !referenceCode.trim().isEmpty();
    }
}