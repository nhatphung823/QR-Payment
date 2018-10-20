package com.ecpay.primitive;

import com.ecpay.packager.QRStringFieldPackager;

/**
 * Created by Joe on October, 18 2018 .
 */
public class CHAR extends QRStringFieldPackager {
  public CHAR(int length, String description){
    super(length, description,"^[a-zA-Z0-9.]+$");
  }
}
