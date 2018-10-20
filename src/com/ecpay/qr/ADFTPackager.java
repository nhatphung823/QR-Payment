package com.ecpay.qr;

/**
 * Created by Joe on October, 18 2018 .
 */

import com.ecpay.packager.QRBasePackager;
import com.ecpay.packager.QRStringFieldPackager;
import com.ecpay.primitive.CHAR;

/**
 * Additional Data Field Template
 */
public class ADFTPackager extends QRBasePackager {
  protected QRStringFieldPackager fld[] = {
      /*00*/ null,
      /*01*/ new CHAR(25, "Bill number"),
      /*02*/ new CHAR(25, "Mobile number"),
      /*03*/ new CHAR(25, "Store label"),
      /*04*/ new CHAR(25, "Loyalty number"),
      /*05*/ new CHAR(25, "Reference label"),
      /*06*/ new CHAR(25, "Customer label"),
      /*07*/ new CHAR(25, "Terminal label"),
      /*08*/ new CHAR(25, "Purpose label"),
      /*09*/ null,
      /*10*/ null,
      /*11*/ null,
      /*12*/ null,
      /*13*/ null,
      /*14*/ null,
      /*15*/ null,
      /*16*/ null,
      /*17*/ null,
      /*18*/ null,
      /*19*/ null,
      /*20*/ null,
      /*21*/ null,
      /*22*/ null,
      /*23*/ null,
      /*24*/ null,
      /*25*/ null,
      /*26*/ null,
      /*27*/ null,
      /*28*/ null,
      /*29*/ null,
      /*30*/ null,
      /*31*/ null,
      /*32*/ null,
      /*33*/ null,
      /*34*/ null,
      /*35*/ null,
      /*36*/ null,
      /*37*/ null,
      /*38*/ null,
      /*39*/ null,
      /*40*/ null,
      /*41*/ null,
      /*42*/ null,
      /*43*/ null,
      /*44*/ null,
      /*45*/ null,
      /*46*/ null,
      /*47*/ null,
      /*48*/ null,
      /*49*/ null,
      /*50*/ null,
      /*51*/ null,
      /*53*/ null,
      /*54*/ null,
      /*55*/ null,
      /*56*/ null,
      /*57*/ null,
      /*58*/ null,
      /*59*/ null,
      /*60*/ null,
      /*61*/ null,
      /*62*/ null,
      /*63*/ null,
      /*64*/ null,
      /*65*/ null,
      /*66*/ null,
      /*67*/ null,
      /*68*/ null,
      /*69*/ null,
      /*70*/ null,
      /*71*/ null,
      /*72*/ null,
      /*73*/ null,
      /*74*/ null,
      /*75*/ null,
      /*76*/ null,
      /*77*/ null,
      /*78*/ null,
      /*79*/ null,
      /*80*/ null,
      /*81*/ null,
      /*82*/ null,
      /*83*/ null,
      /*84*/ null,
      /*85*/ null,
      /*86*/ null,
      /*87*/ null,
      /*88*/ null,
      /*89*/ null,
      /*90*/ null,
      /*91*/ null,
      /*92*/ null,
      /*93*/ null,
      /*94*/ null,
      /*95*/ null,
      /*96*/ null,
      /*97*/ null,
      /*98*/ null,
      /*99*/ null
  };

  public ADFTPackager() {
    super();
    setFieldPackager(fld);
  }
}