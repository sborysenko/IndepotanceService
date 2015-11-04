package com.inatec.pgw.indepotance.domain;

import java.io.Serializable;

/**
 * Created by Sergey on 31.10.2015.
 */
public enum TransactionType implements Serializable {
    Authorisation, FinalAuthorisation, Capture, PartialCapture, PreAuthorisation,PreAuthorisationCompletion,
    Refund, VoidAuth, VoidRefund, VoidCapture, CardholderFundsTransfer, ReversalAuth, Recurring, Subscription,
    CardVerification, Installment, Credit, CaptureDelay, CaptureRelease, AVSCheck, TransactionSetUp, CreditBatch,
    Chargeback, Chargeback2nd, RetrievalRequest, RePresent, GetToken, GetCardNumber, Transfer, DummyTransaction,
    AutoPreAuthCompletion;
}
