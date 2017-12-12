package cn.exception;

public class NotStockException extends RuntimeException {

  /**
   * @fieldName: serialVersionUID
   * @fieldType: long
   * @Description: TODO 
   */
  private static final long serialVersionUID = 1L;

  public NotStockException() {
    super();
  }

  public NotStockException(String s) {
    super(s);
  }

}
