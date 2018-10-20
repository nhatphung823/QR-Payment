package com.ecpay.qr;

import com.ecpay.ex.QRException;

/**
 * Created by Joe on October, 18 2018 .
 */
public class Test {
  public static void main(String... args) throws QRException {
    QRMsg msgO = new QRMsg(new EPackager());
    msgO.setPFI("00");
    msgO.set(1,"11");
    msgO.set(26, new MAIPackager(), new int[]{0,1}, new String[]{"vn.edong.www","0301175691"});
    msgO.set(52,"5411");
    msgO.set(53,"704");
    msgO.set(58,"VN");
    msgO.set(59,"ECPAY.JSC");
    msgO.set(60, "HAN");
    msgO.set(62, new ADFTPackager(), new int[]{3,7}, new String[]{"BADINH","0001"});
    msgO.set(63,"00FF");
    msgO.set(1000,"ok");
    System.out.println(msgO.pack());
    String s = "00020001021126300012vn.edong.www011003011756915204541153037045802VN5909ECPAY.JSC6003HAN62180306BADINH07040001630400FF";// // // // // // // // //
    QRMsg msgI = new QRMsg(new EPackager());
    msgI.unpack(s);
    System.out.println(msgI.trace(1));
  }
}
