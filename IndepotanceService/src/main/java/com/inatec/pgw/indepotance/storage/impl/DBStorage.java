package com.inatec.pgw.indepotance.storage.impl;

import com.inatec.pgw.indepotance.domain.Transaction;
import com.inatec.pgw.indepotance.storage.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Anatoly Chernysh
 */
public class DBStorage implements Storage {

    private static Logger logger = LogManager.getLogger(DBStorage.class);

    @Override
    public Transaction get(String transactionKey) {
        try {
            PreparedStatement queryTransaction = DataSource.getInstance().getConnection().prepareStatement("SELECT * FROM payments WHERE PAYMENTINFO = ?");
            queryTransaction.setString(1, transactionKey);

            ResultSet resultSet = queryTransaction.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionID(resultSet.getLong(3));
                transaction.setCardNumber(resultSet.getLong(4));
                transaction.setBaseCurrency(resultSet.getString(6));
                transaction.setAmount(new BigDecimal(resultSet.getLong(9)));
                return transaction;
            }

//            queryTransaction.
        }
        catch (Exception e) {
            logger.error("DB get: ", e);
            return null;
        }

        return null;
    }

    @Override
    public void put(String transactionKey, Transaction transaction) {
        try {
            PreparedStatement insertTransaction = DataSource.getInstance().getConnection().prepareStatement("INSERT INTO payments (BATCHID, TRANSACTIONID, MERCHANTID, PAYMENTTYPEID, AMOUNT, PAYMENTINFO, CURRENCY) VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertTransaction.setString(1, "1");
            insertTransaction.setString(2, String.valueOf(transaction.getTransactionID()));
            insertTransaction.setString(3, String.valueOf(transaction.getMerchantID()));
            insertTransaction.setString(4, String.format("%12d", transaction.getCardNumber()).substring(0, 11));
            insertTransaction.setString(5, transaction.getAmount().toString());
            insertTransaction.setString(6, transactionKey);
            insertTransaction.setString(7, transaction.getBaseCurrency());
            insertTransaction.execute();
        }
        catch (Exception e) {
            logger.error("DB put: ", e);
        }
    }
}