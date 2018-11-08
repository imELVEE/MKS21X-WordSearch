public class WordSearch{
  private char[][] data;

  public WordSearch(int rows, int cols){
    data = new char[rows][cols];
    clear();
  }

  private void clear(){
    for (int row = 0 ; row < data.length ; row++){
      for (int e = 0 ; e < data[row].length ; e++){
        data[row][e] = '_';
      }
    }
  }

  public String toString(){
    String grid = "";
    for (int row = 0 ; row < data.length ; row++){
      for  (int e = 0 ; e < data[row].length ; e++){
        grid += data[row][e] + " ";
      }
      grid += "\n";
    }
    return grid;
  }

  

}
