package com.ecpay.packager;

import com.ecpay.ex.QRException;
import com.ecpay.abs.QRComponent;
import com.ecpay.qr.QRMsg;

/**
 * Created by Joe on October, 18 2018 .
 */
public class QRMsgFieldPackager extends QRFieldPackager {
  protected QRPackager msgPackager;
  protected QRFieldPackager fieldPackager;

  public QRMsgFieldPackager(QRFieldPackager fieldPackager, QRPackager msgPackager) {
    super(fieldPackager.getLength(), fieldPackager.getDescription());
    this.msgPackager = msgPackager;
    this.fieldPackager = fieldPackager;
  }

  @Override
  public String pack(QRComponent c) throws QRException {
    String data = fieldPackager.pack(c);
    return getPadding((Integer) c.getKey()) + getPadding(data.length()) + data;
  }

  @Override
  public int unpack(QRComponent c, String s) throws QRException {
    int index = Integer.parseInt(s.substring(0, 2));
    int length = Integer.parseInt(s.substring(2, 4));
//    System.out.println("index : " + index);
//    System.out.println("length : " + length);
//    System.out.println("data : " + s.substring(4, length));
    String data = s.substring(4, 4 + length);
    QRMsg msg = new QRMsg(index, msgPackager);
    msgPackager.unpack(msg, data);
    ((QRMsg) c).set(msg);
//    System.out.println("ccc : " + msg.pack());
    return length + 4;
  }
}
