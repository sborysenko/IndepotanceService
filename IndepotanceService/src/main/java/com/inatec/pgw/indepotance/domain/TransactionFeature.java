package com.inatec.pgw.indepotance.domain;

import java.io.Serializable;

/**
 * Created by Sergey on 31.10.2015.
 */
public enum TransactionFeature implements Serializable {
    Secure3D, OneStepTransaction, TwoStepTransaction, CardPresent, CardNotPresent, Token, DynamicDescriptor;
}
