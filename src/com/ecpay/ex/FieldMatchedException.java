package com.ecpay.ex;

/**
 * Created by Joe on October, 19 2018 .
 */
public class FieldMatchedException extends QRException {
  public FieldMatchedException(boolean type, int index, String data, String className) {
    super("Problem " + (type ? "packing" : "unpacking") + " field " + index + "(data " + data
        + " not matched " + className + ")");
  }
}
