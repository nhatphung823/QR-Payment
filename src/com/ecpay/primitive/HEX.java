package com.ecpay.primitive;

import com.ecpay.packager.QRStringFieldPackager;

/**
 * Created by Joe on October, 19 2018 .
 */
public class HEX extends QRStringFieldPackager {
  public HEX(int length, String description){
    super(length, description,"^[A-F0-9]+$");
  }
}
