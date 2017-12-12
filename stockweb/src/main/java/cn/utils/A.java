package cn.utils;

import java.util.ArrayList;
import java.util.List;

public class A {
  private String s = "s";

  private byte bytee = 1;
  private Short st = 1;
  private int i = 1;
  private Long l = 123L;

  private Character c = 'b';
  private Double d = 12.334d;
  private Float f = 1.1f;
  private Boolean b = true;
  
  private String[] ss = {"a","b","c"};
  
  private Integer[] ii = {4,5,6};
  
  private List list;

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  public Integer getI() {
    return i;
  }

  public void setI(Integer i) {
    this.i = i;
  }

  public Boolean getB() {
    return b;
  }

  public void setB(Boolean b) {
    this.b = b;
  }

  public byte getBytee() {
    return bytee;
  }

  public void setBytee(byte bytee) {
    this.bytee = bytee;
  }

  public Short getSt() {
    return st;
  }

  public void setSt(Short st) {
    this.st = st;
  }

  public Long getL() {
    return l;
  }

  public void setL(Long l) {
    this.l = l;
  }

  public Character getC() {
    return c;
  }

  public void setC(Character c) {
    this.c = c;
  }

  public Double getD() {
    return d;
  }

  public void setD(Double d) {
    this.d = d;
  }

  public Float getF() {
    return f;
  }

  public void setF(Float f) {
    this.f = f;
  }

  public void setI(int i) {
    this.i = i;
  }

  public String[] getSs() {
    return ss;
  }

  public void setSs(String[] ss) {
    this.ss = ss;
  }

  public Integer[] getIi() {
    return ii;
  }

  public void setIi(Integer[] ii) {
    this.ii = ii;
  }

  public List getList() {
    list = new ArrayList<>();
    String[] s = new String[]{"l","i","s","t"};
    list.add(s);
    return list;
  }

  public void setList(List<?> list) {
    this.list = list;
  }
}
