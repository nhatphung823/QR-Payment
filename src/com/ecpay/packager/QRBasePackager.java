package com.ecpay.packager;

import com.ecpay.ex.FieldOutboundException;
import com.ecpay.ex.QRException;
import com.ecpay.abs.QRComponent;
import com.ecpay.qr.QRMsg;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by Joe on October, 18 2018 .
 */
public abstract class QRBasePackager implements QRPackager {
  protected QRFieldPackager[] fld;

  @Override
  public String pack(QRComponent m) throws QRException {
    QRComponent c;
    StringBuilder sb = new StringBuilder();
    int tmpMaxField = Math.min(m.getMaxField(), fld.length);
    Map fields = m.getChildren();
    for (int i = 0; i <= tmpMaxField; i++) {
      if ((c = (QRComponent) fields.get(i)) != null) {
        try {
          QRFieldPackager fp = fld[i];
          if (fp == null)
            throw new QRException("null field(" + i + ") packager");
          String val = c.getValue() instanceof String ? (String) c.getValue() : (c.getValue() instanceof QRComponent ? ((QRComponent) c.getValue()).pack() : "");
          if (val.length() < 1) {
            throw new QRException("empty value(" + i + ")");
          }
          if (val.length() > fp.getLength()) {
            throw new FieldOutboundException(true, i, fp.getLength(), val.toString().length());
          }
          sb.append(fp.pack(c));
        } catch (QRException e) {
          throw e;
        }
      }
    }
    return sb.toString();
  }

  @Override
  public int unpack(QRComponent m, String s) throws QRException {
    int consumed = 0;
    String temp = s;
    while (temp != null && !temp.isEmpty()) {
      try {
        int index = Integer.parseInt(temp.substring(0, 2));
        QRFieldPackager field = fld[index];
        int pos = field.unpack(m, temp);
        temp = temp.substring(pos, temp.length());
        consumed += pos;
      } catch (NumberFormatException nfe) {
        throw new QRException(getStackTraceException(nfe));
      }
    }
    return consumed;
  }

  public String getFieldDescription(QRComponent m, int fldNumber) {
    return fld[fldNumber].getDescription();
  }

  public String getDescription() {
    return getClass().getName();
  }

  public QRMsg createQRMsg() {
    return new QRMsg(this);
  }

  public void setFieldPackager(QRFieldPackager[] fld) {
    this.fld = fld;
  }

  protected String getStackTraceException(Throwable ex) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    ex.printStackTrace(printWriter);
    printWriter.close();
    try {
      stringWriter.close();
    } catch (IOException e) {
      return ex.toString();
    }
    return stringWriter.toString();
  }
}
