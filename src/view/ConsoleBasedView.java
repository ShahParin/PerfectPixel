package view;


public class ConsoleBasedView implements ImageView {

  @Override
  public void printStatements(String statement) {

    System.out.println(statement);

  }
}
