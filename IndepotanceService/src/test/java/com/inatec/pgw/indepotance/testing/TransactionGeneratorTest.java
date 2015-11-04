package com.inatec.pgw.indepotance.testing;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.sun.org.apache.xalan.internal.xsltc.runtime.StringValueHandler;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

/**
 * Created by Sergey on 01.11.2015.
 */
public class TransactionGeneratorTest {
    DecimalFormat decFormat = new DecimalFormat("0000.00");

    @Test
    public void testGenerateTransaction() throws Exception {
        for(int i=0; i<500000; i++) {
//            System.out.println(TransactionGenerator.generateTransaction().toString());
            System.out.println(i + " - " + printOutTransaction(new TransactionGenerator().generateTransaction()));
        }
    }

    private String printOutTransaction(Transaction transaction) {
        StringBuilder out = new StringBuilder();

        out.append("[");
        out.append(getPredefinedWidthString(6, transaction.getTransactionID())).append(" ");
        out.append(getPredefinedWidthString(2, transaction.getMerchantID())).append(" ");
        out.append(decFormat.format(transaction.getAmount())).append(" ");
        out.append(transaction.getTransactionCurrency()).append(" ");
        out.append(getPredefinedWidthString(30, transaction.getFirstName())).append(" ");
        out.append(getPredefinedWidthString(30, transaction.getLastName()));
        out.append("]");

        return out.toString();
    }

    private String getPredefinedWidthString(int charsCount, String value) {
        StringBuilder out = new StringBuilder();

        out.append(value);
        while(out.length() <= charsCount) out.insert(0, " ");

        return out.toString();
    }
    private String getPredefinedWidthString(int charsCount, long value) {
        StringBuilder out = new StringBuilder();

        out.append(value);
        while(out.length() <= charsCount) out.insert(0, " ");

        return out.toString();
    }
}