package com.ecpay.packager;

import com.ecpay.ex.QRException;
import com.ecpay.abs.QRComponent;
import com.ecpay.qr.QRMsg;

/**
 * Created by Joe on October, 18 2018 .
 */
public interface QRPackager {
  public String pack (QRComponent m)  throws QRException;

  public int unpack (QRComponent m, String s)  throws QRException;

  public String getDescription();

  public String getFieldDescription(QRComponent m, int fldNumber);

  public QRMsg createQRMsg ();
}
