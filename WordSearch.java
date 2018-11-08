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

  public boolean addWordHorizontal(String word, int row, int col){
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (data[row].length - col < word.length() || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int e = col ; e < data[row].length ; e++){
      if (data[row][e] != '_' && data[row][e] != word.charAt(e - col)){
        return false;
      }
    }

    //passed all fail cases
    for (int e = col ; e < data[row].length ; e++){
      data[row][e] = word.charAt(e - col);
    }
    return true;
  }


}
