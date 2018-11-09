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
    for (int e = col ; e < data.length ; e++){
      if (data[row][e] != '_' && data[row][e] != word.charAt(e - col)){
        return false;
      }
    }

    //passed all fail cases
    for (int e = col ; e - col < word.length() ; e++){
      data[row][e] = word.charAt(e - col);
    }
    return true;
  }

  public boolean addWordVertical(String word, int row, int col){
    //if the word doesn't fit or row doesn't exist
    if (data.length - row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int r = row ; r < data.length ; r++){
      if (data[r][col] != '_' && data[r][col] != word.charAt(r - row)){
        return false;
      }
    }

    //passed all fail cases
    for (int r = row ; r - row < word.length() ; r++){
      data[r][col] = word.charAt(r - row);
    }
    return true;
  }

  public boolean addWordDiagonal(String word, int row, int col){
    //Use the fail cases of addWordHorizontal
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (data[row].length - col < word.length() || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int e = col ; e < data.length ; e++){
      if (data[row][e] != '_' && data[row][e] != word.charAt(e - col)){
        return false;
      }
    }

    //Use the fail cases of addWordVertical
    //if the word doesn't fit or row doesn't exist
    if (data.length - row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int r = row ; r < data.length ; r++){
      if (data[r][col] != '_' && data[r][col] != word.charAt(r - row)){
        return false;
      }
    }

    //passed al fail casrs
    int e = col;
    for (int r = row ; r - row < word.length() ;){
      data[r][e] = word.charAt(r - row);
      e++;
      r++;
    }
    return true;
  }

}
