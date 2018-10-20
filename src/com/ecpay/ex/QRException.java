package com.ecpay.ex;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Joe on October, 18 2018 .
 */
public class QRException extends Exception {
  Throwable nested = null;

  public QRException() {
    super();
  }

  public QRException(String s) {
    super(s);
  }

  public QRException (Throwable nested) {
    super(nested.toString());
    this.nested = nested;
  }

  public QRException (String s, Throwable nested) {
    super(s);
    this.nested = nested;
  }

  public Throwable getNested() {
    return nested;
  }

  protected String getTagName() {
    return "qr-exception";
  }
  public void dump (PrintStream p, String indent) {
    String inner = indent + "  ";
    p.println (indent + "<"+getTagName()+">");
    p.println (inner  + getMessage());
    if (nested != null) {
      if (nested instanceof QRException)
        ((QRException)nested).dump (p, inner);
      else {
        p.println (inner + "<nested-exception>");
        p.print   (inner);
        nested.printStackTrace (p);
        p.println (inner + "</nested-exception>");
      }
    }
    p.print (inner);
    printStackTrace (p);
    p.println (indent + "</"+getTagName()+">");
  }
  public String toString() {
    StringBuffer buf = new StringBuffer (super.toString());
    if (nested != null)
      buf.append (" (" + nested.toString() +")");
    return buf.toString();
  }

  public void printStackTrace() {
    super.printStackTrace();
    if (nested != null) {
      System.err.print("Nested:");
      nested.printStackTrace();
    }
  }

  public void printStackTrace(PrintStream ps) {
    super.printStackTrace(ps);
    if (nested != null) {
      ps.print("Nested:");
      nested.printStackTrace(ps);
    }
  }

  public void printStackTrace(PrintWriter pw) {
    super.printStackTrace(pw);
    if (nested != null) {
      pw.print("Nested:");
      nested.printStackTrace(pw);
    }
  }
}
