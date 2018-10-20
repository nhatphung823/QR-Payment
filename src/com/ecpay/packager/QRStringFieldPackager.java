package com.ecpay.packager;

import com.ecpay.ex.FieldMatchedException;
import com.ecpay.ex.FieldOutboundException;
import com.ecpay.ex.QRException;
import com.ecpay.abs.QRComponent;
import com.ecpay.qr.QRMsg;

/**
 * Created by Joe on October, 18 2018 .
 */
public class QRStringFieldPackager extends QRFieldPackager {
  private String regex;

  public QRStringFieldPackager(int len, String description, String regex) {
    super(len, description);
    this.regex = regex;
  }

  @Override
  public String pack(QRComponent c) throws QRException {
    Object data = c.getValue();
    if (data instanceof String) {
      if (!((String) data).matches(regex))
        throw new FieldMatchedException(true, (Integer) c.getKey(), data.toString(), this.getClass().getSimpleName());
      return getPadding((Integer) c.getKey()) + getPadding(((String) data).length()) + data;
    } else if (data instanceof QRComponent) {
      return ((QRComponent) data).pack();
    } else {
      throw new QRException("fuck the hell");
    }
  }

  @Override
  public int unpack(QRComponent c, String s) throws QRException {
    int index = Integer.parseInt(s.substring(0, 2));
    int length = Integer.parseInt(s.substring(2, 4));
    if (length > this.getLength()) {
      throw new FieldOutboundException(false, index, this.getLength(), length);
    }
    String data = s.substring(4, 4 + length);
    if (!data.matches(regex))
      throw new FieldMatchedException(false, index, data, this.getClass().getSimpleName());
    ((QRMsg) c).set(index, data);
    return 4 + length;
  }
}
