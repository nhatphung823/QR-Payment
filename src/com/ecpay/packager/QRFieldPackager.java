package com.ecpay.packager;

import com.ecpay.ex.QRException;
import com.ecpay.abs.QRComponent;

/**
 * Created by Joe on October, 18 2018 .
 */
public abstract class QRFieldPackager {
  private int len;
  private String description;

  public abstract String pack (QRComponent c) throws QRException;

  public abstract int unpack(QRComponent c, String s) throws QRException;

  public QRFieldPackager() {
    this.len = -1;
    this.description = null;
  }

  public QRFieldPackager(int len, String description) {
    this.len = len;
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getLength() {
    return len;
  }

  public void setLength(int len) {
    this.len = len;
  }

  protected String getPadding(int num) {
    return num < 10 ? "0" + num : String.valueOf(num);
  }
}
