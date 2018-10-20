package com.ecpay.abs;

import com.ecpay.ex.QRException;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Joe on October, 18 2018 .
 */
public abstract class QRComponent implements Cloneable {
  public abstract String pack() throws QRException;
  public abstract int unpack(String s)  throws QRException;

  public Object getKey()throws QRException {
    throw new QRException ("N/A in Composite");
  }

  public Object getValue() throws QRException {
    throw new QRException ("N/A in Composite");
  }

  public abstract void setFieldNumber (int fieldNumber);

  public abstract void setValue(Object obj) throws QRException;

  public Map getChildren() {
    return new Hashtable();
  }

  public int getMaxField() {
    return 0;
  }
}
