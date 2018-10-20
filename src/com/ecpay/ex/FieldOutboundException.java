package com.ecpay.ex;

/**
 * Created by Joe on October, 19 2018 .
 */
public class FieldOutboundException extends QRException {
  public FieldOutboundException(boolean type, int index, int fieldLength, int dataLength) {
    super("Problem " + (type ? "packing" : "unpacking") + " field " + index + "(Field length " + fieldLength
        + " too long. Max: " + dataLength + ")");
  }
}
