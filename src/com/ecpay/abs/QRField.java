package com.ecpay.abs;

import com.ecpay.abs.QRComponent;
import com.ecpay.ex.QRException;

import java.io.Serializable;

/**
 * Created by Joe on October, 18 2018 .
 */
public class QRField extends QRComponent implements Cloneable, Serializable {
  protected int fieldNumber;
  protected String value;

  @Override
  public String pack() throws QRException {
    throw new QRException("Not available on Leaf");
  }

  @Override
  public int unpack(String s) throws QRException {
    throw new QRException("Not available on Leaf");
  }

  @Override
  public Object getKey() {
    return fieldNumber;
  }

  @Override
  public Object getValue() throws QRException {
    return value;
  }

  public void setFieldNumber(int fieldNumber) {
    this.fieldNumber = fieldNumber;
  }

  public void setValue(Object obj) {
    if (obj instanceof String)
      value = (String) obj;
    else
      value = obj.toString();
  }

  public QRField(int n, String v) {
    fieldNumber = n;
    value = v;
  }

  @Override
  public String toString() {
    return "QRField{" +
        "fieldNumber=" + fieldNumber +
        ", value='" + value + '\'' +
        '}';
  }
}
