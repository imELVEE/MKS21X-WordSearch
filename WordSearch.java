import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
  private static char[][] data;
  private static int seed;
  private static ArrayList<String>  wordsToAdd = new ArrayList<String>();
  private static ArrayList<String> wordsAdded = new ArrayList<String>();

  public WordSearch(int rows, int cols, String filename, int randseed){
    seed = randseed;
    data = new char[rows][cols];
    try{
      File words = new File(filename);
      Scanner in = new Scanner(words);
      while (in.hasNext()){
        wordsToAdd.add(in.nextLine());
      }
    }
    catch(FileNotFoundException noFile){
      System.out.println("File not found: " + filename + "\nPlease input a file that exists within the same directory as WordSearch.java");
      System.exit(1);
    }
    clear();
  }

  private void clear(){
    for (int row = 0 ; row < data.length ; row++){
      for (int e = 0 ; e < data[row].length ; e++){
        data[row][e] = ' ';
      }
    }
  }

  public String toString(){
    String grid = "|";
    for (int row = 0 ; row < data.length ; row++){
      for  (int e = 0 ; e < data[row].length ; e++){
        grid += data[row][e] + " ";
      }
      grid += "|\n|";
    }
    return grid.substring(0,grid.length()-1);
  }

  public boolean addWord(String word){
    /*
       0 = up
       1 = upright
       2 = right
       3 = downright
       4 = down
       5 = downleft
       6 = left
       7 = upleft
    */
    Random dChooser = new Random(seed);
    int direction = dChooser.nextInt()%8;

    Random rowChooser = new Random(seed);
    int r = Math.abs(rowChooser.nextInt()%data.length);

    Random colChooser = new Random(seed);
    int c = Math.abs(colChooser.nextInt()%data[r].length);

    for (int i = 0 ; i < data.length * data[r].length + 1000 ; i++){
      if (direction == 0){
        if(up(word, r, c))
          return true;
      }
      else if(direction == 1){
        if(rightUp(word, r, c))
          return true;
        }
      else if(direction == 2){
        if(right(word, r, c))
          return true;
      }
      else if(direction == 3){
        if(rightDown(word, r, c))
          return true;
      }
      else if(direction == 4){
        if(down(word, r, c))
        return true;
      }
      else if(direction == 5){
        if(leftDown(word, r, c))
        return true;
      }
      else if(direction == 6){
        if(left(word, r, c))
        return true;
      }
      else if(direction == 7){
        if(leftUp(word, r, c))
        return true;
      }
      direction = dChooser.nextInt()%8;
      r = Math.abs(rowChooser.nextInt()%data.length);
      c = Math.abs(colChooser.nextInt()%data[r].length);
    }
  return false;
  }

  public boolean right(String word, int row, int col){
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (data[row].length - col < word.length() || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int e = col ; e - col < word.length() ; e++){
      if (data[row][e] != ' ' && data[row][e] != word.charAt(e - col)){
        return false;
      }
    }

    //passed all fail cases
    for (int e = col ; e - col < word.length() ; e++){
      data[row][e] = word.charAt(e - col);
    }
    return true;
  }

//---------------------------------------------------------------------------------------------------------------------------
  public boolean left(String word, int row, int col){
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (col < word.length() - 1 || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int e = col ; col - e < word.length() ; e--){
      if (data[row][e] != ' ' && data[row][e] != word.charAt(col - e)){
        return false;
      }
    }

    //passed all fail cases
    for (int e = col ; col - e < word.length() ; e--){
      data[row][e] = word.charAt(col - e);
    }
    return true;
  }
//---------------------------------------------------------------------------------------------------------------------------

  public boolean down(String word, int row, int col){
    //if the word doesn't fit or row doesn't exist
    if (data.length - row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int r = row ; r - row < word.length() ; r++){
      if (data[r][col] != ' ' && word.length() > r - row && data[r][col] != word.charAt(r - row)){
        return false;
      }
    }

    //passed all fail cases
    for (int r = row ; r - row < word.length() ; r++){
      data[r][col] = word.charAt(r - row);
    }
    return true;
  }

//---------------------------------------------------------------------------------------------------------------------------
  public boolean up(String word, int row, int col){
    //if the word doesn't fit or row doesn't exist
    if (row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //check if the letters don't overlap
    for (int r = row ; r > -1 ; r--){
      if (data[r][col] != ' ' && word.length() - 1 > row - r && data[r][col] != word.charAt(row - r)){
        return false;
      }
    }

    //passed all fail cases
    for (int r = row ; row - r < word.length() ; r--){
      data[r][col] = word.charAt(row - r);
    }
    return true;
  }
//---------------------------------------------------------------------------------------------------------------------------

  public boolean rightDown(String word, int row, int col){
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (data[row].length - col < word.length() || col < 0){
      return false;
    }

    //if the word doesn't fit or row doesn't exist
    if (data.length - row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //---
    //check if the letters don't overlap
    int e = col;
    for (int r = row ; r - row < word.length() && e - col < word.length() ; r++){
      if (data[r][e] != ' ' && data[r][e] != word.charAt(r - row)){
        return false;
      }
      e++;
    }

    //passed all fail cases
    e = col;
    for (int r = row ; r - row < word.length() ;){
      data[r][e] = word.charAt(r - row);
      e++;
      r++;
    }
    return true;
  }

  public boolean rightUp(String word, int row, int col){
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (data[row].length - col < word.length() || col < 0){
      return false;
    }

    //if the word doesn't fit or row doesn't exist
    if (row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //---
    //check if the letters don't overlap
    int e = col;
    for (int r = row ; row - r < word.length() &&  e - col < word.length() ; r--){
      if (data[r][e] != ' ' && data[r][e] != word.charAt(row - r)){
        return false;
      }
      e++;
    }

    //passed all fail cases
    e = col;
    for (int r = row ; row - r < word.length() ;){
      data[r][e] = word.charAt(row - r);
      e++;
      r--;
    }
    return true;
  }

  public boolean leftDown(String word, int row, int col){
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (col < word.length() - 1 || col < 0){
      return false;
    }

    //if the word doesn't fit or row doesn't exist
    if (data.length - row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //---
    //check if the letters don't overlap
    int e = col;
    for (int r = row ; r - row < word.length() && col - e < word.length() ; r++){
      if (data[r][e] != ' ' && data[r][e] != word.charAt(r - row)){
        return false;
      }
      e--;
    }

    //passed all fail cases
    e = col;
    for (int r = row ; r - row < word.length() && col - e < word.length() ;){
      data[r][e] = word.charAt(r - row);
      e--;
      r++;
    }
    return true;
  }

  public boolean leftUp(String word, int row, int col){
    //if row doesn't exist
    if (data.length < row || row < 0){
      return false;
    }

    //if the word doesn't fit or col doesn't exist
    if (col < word.length() - 1 || col < 0){
      return false;
    }

    //if the word doesn't fit or row doesn't exist
    if (row < word.length() || row < 0){
      return false;
    }

    //if col doesn't exist
    if (data[row].length < col || col < 0){
      return false;
    }

    //---
    //check if the letters don't overlap
    int e = col;
    for (int r = row ; row - r < word.length() && col - e < word.length() ; r--){
      if (data[r][e] != ' ' && data[r][e] != word.charAt(row - r)){
        return false;
      }
      e--;
    }

    //passed all fail cases
    e = col;
    for (int r = row ; row - r < word.length() ;){
      data[r][e] = word.charAt(row - r);
      e--;
      r--;
    }
    return true;
  }


  public void fillIn(){
    Random inc = new Random(seed);
    for (int i = 0 ; i < data.length ; i++){
      for (int e = 0 ; e < data[i].length ; e++){
        if (data[i][e] == ' '){
          data[i][e] = (char)((int)'A' + Math.abs(inc.nextInt()%26));
        }
      }
    }
  }


  public static void main(String[] args){
    //rows, cols, filname, [randomseed] [answers]
    WordSearch grid;
    if (args.length < 3){
      System.out.println("Please type in the number of rows, columns, and the file of words you wish to create the word search with. \nExample: java WordSearch 10 10 words.txt");
      System.exit(1);
    }
    else{
      try{
        if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[1]) < 1){
          System.out.println("Please make rows and columns greater than 0.");
          System.exit(1);
        }
      }
      catch(NumberFormatException r){
        System.out.println("Please enter valid numbers for rows and columns.");
        System.exit(1);
      }
    }
    if (args.length > 3){
      try{
        Integer.parseInt(args[3]);
      }
      catch(NumberFormatException nn){
        System.out.println("Seed is not a number. Please enter a number between 0 and 10000 inclusive.");
        System.exit(1);
      }
    }
    if (args.length > 3 && Integer.parseInt(args[3]) >= 0 && Integer.parseInt(args[3]) <= 10000){
      grid = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], Integer.parseInt(args[3]));
    }
    else{
      grid = new WordSearch(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2], seed);
    }

    System.out.println();

    /*
    grid.right("get", 0, 0);
    grid.left("let", 4, 3);
    grid.down("go", 0, 0);
    grid.up("lie", 4, 3);
    grid.rightDown("gin", 0, 0);
    grid.rightUp("bin", 4, 0);
    grid.leftDown("free", 0, 3);
    grid.leftUp("belt", 7, 4);
    System.out.println(grid);
    */
    for (int i = 0 ; i < wordsToAdd.size() ; i++){
      if(grid.addWord(wordsToAdd.get(i).toUpperCase())){
        //i++;
        wordsAdded.add(wordsToAdd.get(i));
      }
    }
    if (args.length > 3 && args[args.length - 1].equals("key")){
    }
    else{
      grid.fillIn();
    }
    System.out.println(grid);
    System.out.println(wordsAdded);
  }

}
