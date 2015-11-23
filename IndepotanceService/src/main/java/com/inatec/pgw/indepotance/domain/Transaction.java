package com.inatec.pgw.indepotance.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Sergey on 31.10.2015.
 */
public class Transaction implements Serializable {
    private long id;
    private long transactionID;
    private long merchantID;
    private long terminalID;
    private long cardNumber;
    private int pin;
    private int CVV;
    private Date cardExpirationDate;
    private String FirstName;
    private String LastName;
    private String billingAddress;
    private BigDecimal amount;
    private String transactionCurrency;
    private String settlementCurrency;
    private String baseCurrency;
    private String reportingCurrency;
    private TransactionType transactionType;
    private TransactionFeature transactionFeature;
    private PaymentMethod paymentMethod;
    private TransactionStatus transactionStatus;
    private String errorCode;
    private String errorMessage;
    private Date transactionStartDate;
    private Date transactionEndDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public long getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(long merchantID) {
        this.merchantID = merchantID;
    }

    public long getTerminalID() {
        return terminalID;
    }

    public void setTerminalID(long terminalID) {
        this.terminalID = terminalID;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCVV() {
        return CVV;
    }

    public void setCVV(int CVV) {
        this.CVV = CVV;
    }

    public Date getCardExpirationDate() {
        return cardExpirationDate;
    }

    public void setCardExpirationDate(Date cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public String getReportingCurrency() {
        return reportingCurrency;
    }

    public void setReportingCurrency(String reportingCurrency) {
        this.reportingCurrency = reportingCurrency;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionFeature getTransactionFeature() {
        return transactionFeature;
    }

    public void setTransactionFeature(TransactionFeature transactionFeature) {
        this.transactionFeature = transactionFeature;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getTransactionStartDate() {
        return transactionStartDate;
    }

    public void setTransactionStartDate(Date transactionStartDate) {
        this.transactionStartDate = transactionStartDate;
    }

    public Date getTransactionEndDate() {
        return transactionEndDate;
    }

    public void setTransactionEndDate(Date transactionEndDate) {
        this.transactionEndDate = transactionEndDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", merchantID=" + merchantID +
                ", terminalID=" + terminalID +
                ", cardNumber=" + cardNumber +
                ", pin=" + pin +
                ", CVV=" + CVV +
                ", cardExpirationDate=" + cardExpirationDate +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                ", amount=" + amount +
                ", transactionCurrency='" + transactionCurrency + '\'' +
                ", settlementCurrency='" + settlementCurrency + '\'' +
                ", baseCurrency='" + baseCurrency + '\'' +
                ", reportingCurrency='" + reportingCurrency + '\'' +
                ", transactionType=" + transactionType +
                ", transactionFeature=" + transactionFeature +
                ", paymentMethod=" + paymentMethod +
                ", transactionStatus=" + transactionStatus +
                ", errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", transactionStartDate=" + transactionStartDate +
                ", transactionEndDate=" + transactionEndDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (transactionID != that.transactionID) return false;
        if (merchantID != that.merchantID) return false;
        if (terminalID != that.terminalID) return false;
        if (cardNumber != that.cardNumber) return false;
        if (pin != that.pin) return false;
        if (CVV != that.CVV) return false;
        if (cardExpirationDate != null ? !cardExpirationDate.equals(that.cardExpirationDate) : that.cardExpirationDate != null)
            return false;
        if (FirstName != null ? !FirstName.equals(that.FirstName) : that.FirstName != null) return false;
        if (LastName != null ? !LastName.equals(that.LastName) : that.LastName != null) return false;
        if (billingAddress != null ? !billingAddress.equals(that.billingAddress) : that.billingAddress != null)
            return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (transactionCurrency != null ? !transactionCurrency.equals(that.transactionCurrency) : that.transactionCurrency != null)
            return false;
        if (settlementCurrency != null ? !settlementCurrency.equals(that.settlementCurrency) : that.settlementCurrency != null)
            return false;
        if (baseCurrency != null ? !baseCurrency.equals(that.baseCurrency) : that.baseCurrency != null) return false;
        if (reportingCurrency != null ? !reportingCurrency.equals(that.reportingCurrency) : that.reportingCurrency != null)
            return false;
        if (transactionType != that.transactionType) return false;
        if (transactionFeature != that.transactionFeature) return false;
        if (paymentMethod != that.paymentMethod) return false;
        if (transactionStatus != that.transactionStatus) return false;
        if (errorCode != null ? !errorCode.equals(that.errorCode) : that.errorCode != null) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        if (transactionStartDate != null ? !transactionStartDate.equals(that.transactionStartDate) : that.transactionStartDate != null)
            return false;
        return !(transactionEndDate != null ? !transactionEndDate.equals(that.transactionEndDate) : that.transactionEndDate != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (transactionID ^ (transactionID >>> 32));
        result = 31 * result + (int) (merchantID ^ (merchantID >>> 32));
        result = 31 * result + (int) (terminalID ^ (terminalID >>> 32));
        result = 31 * result + (int) (cardNumber ^ (cardNumber >>> 32));
        result = 31 * result + pin;
        result = 31 * result + CVV;
        result = 31 * result + (cardExpirationDate != null ? cardExpirationDate.hashCode() : 0);
        result = 31 * result + (FirstName != null ? FirstName.hashCode() : 0);
        result = 31 * result + (LastName != null ? LastName.hashCode() : 0);
        result = 31 * result + (billingAddress != null ? billingAddress.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (transactionCurrency != null ? transactionCurrency.hashCode() : 0);
        result = 31 * result + (settlementCurrency != null ? settlementCurrency.hashCode() : 0);
        result = 31 * result + (baseCurrency != null ? baseCurrency.hashCode() : 0);
        result = 31 * result + (reportingCurrency != null ? reportingCurrency.hashCode() : 0);
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (transactionFeature != null ? transactionFeature.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (transactionStatus != null ? transactionStatus.hashCode() : 0);
        result = 31 * result + (errorCode != null ? errorCode.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (transactionStartDate != null ? transactionStartDate.hashCode() : 0);
        result = 31 * result + (transactionEndDate != null ? transactionEndDate.hashCode() : 0);
        return result;
    }
}
