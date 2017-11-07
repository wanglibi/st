package cn.exception;

public class NoWeekException extends RuntimeException {

  /**
   * @fieldName: serialVersionUID
   * @fieldType: long
   * @Description: TODO 
   */
  private static final long serialVersionUID = 1L;

  public NoWeekException() {
    super();
  }

  public NoWeekException(String s) {
    super(s);
  }

}
