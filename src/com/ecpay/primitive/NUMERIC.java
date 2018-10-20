package com.ecpay.primitive;

import com.ecpay.packager.QRStringFieldPackager;

/**
 * Created by Joe on October, 18 2018 .
 */
public class NUMERIC extends QRStringFieldPackager {
  public NUMERIC(int len, String description) {
    super(len, description, "^\\d+\\.?\\d*$");
  }
}
