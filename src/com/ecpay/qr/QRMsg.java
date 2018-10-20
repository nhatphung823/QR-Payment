package com.ecpay.qr;

import com.ecpay.abs.QRComponent;
import com.ecpay.abs.QRField;
import com.ecpay.ex.QRException;
import com.ecpay.packager.QRPackager;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Joe on October, 18 2018 .
 */
public class QRMsg extends QRComponent implements Cloneable, Serializable {
  protected Map fields;
  protected int maxField;
  protected QRPackager packager;
  protected int fieldNumber = -1;

  public QRMsg() {
    fields = new TreeMap();
    maxField = -1;
  }

  public QRMsg(int fieldNumber) {
    this();
    setFieldNumber(fieldNumber);
  }

  public QRMsg(QRPackager packager) {
    this();
    setPackager(packager);
  }

  public QRMsg(int fieldNumber, QRPackager packager) {
    this(fieldNumber);
    setPackager(packager);
  }

  public String pack() throws QRException {
    synchronized (this) {
      return packager.pack(this);
    }
  }

  @Override
  public int unpack(String s) throws QRException {
    synchronized (this) {
      return packager.unpack(this, s);
    }
  }

  public void set(QRComponent c) throws QRException {
    if (c != null) {
      Integer i = (Integer) c.getKey();
      fields.put(i, c);
      if (i.intValue() > maxField) {
        maxField = i.intValue();
      }
    }
  }

  public void set(int fldno, String value) throws QRException {
    if (value != null)
      set(new QRField(fldno, value));
    else
      unset(fldno);
  }

  public void set(int fldno, QRPackager packager, int[] subFields, String[] subValues) throws QRException {
    QRMsg subMsg = new QRMsg(fldno, packager);
    for (int i = 0; i < subFields.length; i++) {
      subMsg.set(subFields[i], subValues[i]);
    }
    set(subMsg);
  }

  public void unset(int fldno) {
    fields.remove(fldno);
  }

  public void setFieldNumber(int fieldNumber) {
    this.fieldNumber = fieldNumber;
  }

  public QRPackager getPackager() {
    return packager;
  }

  public void setPackager(QRPackager packager) {
    this.packager = packager;
  }

  public Map getChildren() {
    return (Map) ((TreeMap) fields).clone();
  }

  public QRComponent getComponent(int fldno) {
    return (QRComponent) fields.get(fldno);
  }

  public Object getValue(int fldno) throws QRException {
    QRComponent c = getComponent(fldno);
    return c != null ? c.getValue() : null;
  }

  public String getString(int fldno) {
    String s = null;
    if (hasField(fldno)) {
      try {
        Object obj = getValue(fldno);
        if (obj instanceof String)
          s = (String) obj;
        else if (obj instanceof QRComponent)
          s = ((QRComponent) obj).pack();
        else
          s = obj.toString();
      } catch (QRException e) {
        e.printStackTrace();
      }
    }
    return s;
  }

  public boolean hasField(int fldno) {
    return fields.get(fldno) != null;
  }

  public boolean hasFields() {
    return !fields.isEmpty();
  }

  public Object getKey() throws QRException {
    if (fieldNumber != -1)
      return fieldNumber;
    throw new QRException("This is not a subField");
  }

  public Object getValue() {
    return this;
  }

  public void setPFI(String pfi) throws QRException {
    set(new QRField(0, pfi));
  }

  public String getPFI() throws QRException {
    if (!hasField(0))
      throw new QRException("PFI not available");
    return (String) getValue(0);
  }

  public void setValue(Object obj) throws QRException {
    throw new QRException("setValue N/A in QRMsg");
  }

  public int getMaxField() {
    return maxField;
  }

  @Override
  public String toString() {
    return "QRMsg{" +
        "fields=" + fields +
        ", maxField=" + maxField +
        ", packager=" + packager +
        ", fieldNumber=" + fieldNumber +
        '}';
  }

  public String trace(int deep) throws QRException {
    StringBuilder sb = new StringBuilder();
    if (!fields.isEmpty()) {
      for (Object k : fields.keySet()) {
        Object v = fields.get(k);
        if (v instanceof QRField) {
          sb.append(getTabs(deep) + k).append(getTabs(deep) + ((QRField) v).getValue()).append("\n");
        } else if (v instanceof QRMsg) {
          sb.append(getTabs(deep) + k).append("\n").append(((QRMsg) v).trace(deep+1));
        } else {
          sb.append("fk\n");
        }
      }
      return sb.toString();
    }
    return "EMPTY";
  }

  String getTabs(int deep) {
    String s = "";
    for (int i = 0; i < deep; i++) {
      s = s + "\t";
    }
    return s;
  }
}
