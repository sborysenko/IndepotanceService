package com.inatec.pgw.indepotance.domain;

import java.io.Serializable;

/**
 * Created by Sergey on 31.10.2015.
 */
public enum PaymentMethod implements Serializable {
    Card, PrePaid, OnlineBankTransfer, DirectDebit, Wallet, Invoice, DirectCarrierBilling, Custom;
}
