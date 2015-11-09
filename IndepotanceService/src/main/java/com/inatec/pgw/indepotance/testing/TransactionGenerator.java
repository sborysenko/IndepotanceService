package com.inatec.pgw.indepotance.testing;

import com.inatec.pgw.indepotance.domain.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * Created by Sergey on 01.11.2015.
 */
public class TransactionGenerator {
    public Transaction generateTransaction() {
        Random random = new Random();

        Transaction transaction = new Transaction();

        transaction.setTransactionID(random.nextInt(1000000)+1);
        transaction.setMerchantID(random.nextInt(40)+10);
        transaction.setTerminalID(random.nextInt(10000)+1);
        transaction.setTransactionCurrency(getRandomCurrency(random));
        transaction.setBaseCurrency("USD");
        transaction.setSettlementCurrency(getRandomCurrency(random));
        transaction.setReportingCurrency(getRandomCurrency(random));
        transaction.setCardNumber(Long.parseLong(RandomStringUtils.randomNumeric(16)));
        transaction.setCVV(Integer.parseInt(RandomStringUtils.randomNumeric(3)));
        transaction.setCardExpirationDate(new Date());
        transaction.setPin(Integer.parseInt(RandomStringUtils.randomNumeric(4)));
        transaction.setAmount(new BigDecimal(random.nextFloat() * (Math.pow(10, random.nextInt(5)))));
//        transaction.setFirstName(RandomStringUtils.randomAlphabetic(random.nextInt(10)+10));
//        transaction.setLastName(RandomStringUtils.randomAlphabetic(random.nextInt(20)+10));
//        transaction.setBillingAddress(RandomStringUtils.randomAlphanumeric(40));
        transaction.setTransactionStartDate(new Date());
        transaction.setTransactionType(getRandomTransactionType(random));
        transaction.setTransactionFeature(getRandomTransactionFeature(random));
        transaction.setTransactionStatus(getRandomTransactionStatus(random));
        transaction.setPaymentMethod(getRandomPaymentMethod(random));
        if (TransactionStatus.Finished.equals(transaction.getTransactionStatus())) {
            transaction.setTransactionEndDate(new Date());
            if (random.nextInt(10) < 3) {
                transaction.setErrorCode(RandomStringUtils.randomNumeric(3));
                transaction.setErrorMessage(RandomStringUtils.randomAlphabetic(30));
            }
        }

        return transaction;
    }

    private static String getRandomCurrency(Random random) {
        return Currencies.values()[random.nextInt(Currencies.values().length)].name();
    }

    private static TransactionType getRandomTransactionType (Random random) {
        return TransactionType.values()[random.nextInt(TransactionType.values().length)];
    }

    private static TransactionFeature getRandomTransactionFeature (Random random) {
        return TransactionFeature.values()[random.nextInt(TransactionFeature.values().length)];
    }

    private static TransactionStatus getRandomTransactionStatus (Random random) {
        return TransactionStatus.values()[random.nextInt(TransactionStatus.values().length)];
    }

    private static PaymentMethod getRandomPaymentMethod (Random random) {
        return PaymentMethod.values()[random.nextInt(PaymentMethod.values().length)];
    }
 }
