public class mydriver{
  public static void main(String[] args){
    WordSearch testGrid = new WordSearch(15, 20);

    //---

    System.out.println("\n---\n//TESTING toString and constructor\n");
    System.out.println(testGrid);

    //---

    System.out.println("\n---\n//TESING addWordHorizontal\n");

    System.out.println("//Should print false because word is out of bounds");
    System.out.println(testGrid.addWordHorizontal("chicken", 0, 14));
    System.out.println(testGrid);

    System.out.println("//Should print false because row does not exist");
    System.out.println(testGrid.addWordHorizontal("chicken", -1, 13));
    System.out.println(testGrid);

    System.out.println("//Should print false because col does not exist");
    System.out.println(testGrid.addWordHorizontal("chicken", 0, -1));
    System.out.println(testGrid);

    System.out.println("//Should print true because word is in bounds");
    System.out.println(testGrid.addWordHorizontal("chicken", 0, 13));
    System.out.println(testGrid);

    System.out.println("//Should print false because letters do not overlap");
    System.out.println(testGrid.addWordHorizontal("chicken", 0, 9));
    System.out.println(testGrid);

    //---

    System.out.println("\n---\n//TESING addWordVertical\n");

    System.out.println("//Should print false because word is out of bounds");
    System.out.println(testGrid.addWordVertical("chicken", 10, 14));
    System.out.println(testGrid);

    System.out.println("//Should print false because row does not exist");
    System.out.println(testGrid.addWordVertical("chicken", -1, 13));
    System.out.println(testGrid);

    System.out.println("//Should print false because col does not exist");
    System.out.println(testGrid.addWordVertical("chicken", 0, -1));
    System.out.println(testGrid);

    System.out.println("//Should print true because word is in bounds");
    System.out.println(testGrid.addWordVertical("chicken", 0, 13));
    System.out.println(testGrid);

    System.out.println("//Should print false because letters do not overlap");
    System.out.println(testGrid.addWordVertical("chicken", 0, 15));
    System.out.println(testGrid);

  }
}
