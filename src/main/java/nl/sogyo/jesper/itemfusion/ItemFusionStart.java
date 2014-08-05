package nl.sogyo.jesper.itemfusion;

/**
 * Created by jvdberg on 24/04/2014.
 */
public class ItemFusionStart {

    public static void main(String[] args) {

        GameDEUS myDEUS = new GameDEUS();
        importGame(myDEUS);

        // generate prices for all items if available in store
//        for (int i = 0; i < myDEUS.itemList.size(); i++) {
//            if (myDEUS.itemList.get(i).getStoreList().size() > 0) {
//                myDEUS.itemList.get(i).setLowestCostStoreAndPrice();
//            }
//        }

        // generate random item to check for
        int testItemNum = 105; //(int) (Math.random()*myDEUS.itemList.size());

        printItemOverview(myDEUS, testItemNum);

        System.out.println();
        System.out.println("Lowest price for " + myDEUS.itemList.get(testItemNum).getName() + ": ");

        int lowestItemPrice = myDEUS.itemList.get(testItemNum).getLowestPrice();
        System.out.println(" ~ " + lowestItemPrice);

        myDEUS.itemList.get(testItemNum).getBestOption();
        System.out.println();
        myDEUS.itemList.get(testItemNum).printTreeBestOptionB("");

        for (int i = 0; i < myDEUS.itemList.size(); i++)
        System.out.println(myDEUS.itemList.get(i).getFusionList().size());

        System.out.println(myDEUS.itemList.get(55).getName());
        System.out.println(myDEUS.itemList.get(55).getFusionList().size());

        System.out.println(myDEUS.itemList.get(59).getName());
        System.out.println(myDEUS.itemList.get(59).getFusionList().size());

        System.out.println(myDEUS.itemList.get(60).getName());
        System.out.println(myDEUS.itemList.get(60).getFusionList().size());



    }

    public static void importGame(GameDEUS myGame) {
        myGame.fileReader();
        printImport(myGame);
    }

    private static void printImport(GameDEUS myGame) {
        System.out.println();
        System.out.println("Amount of stores: " + myGame.storeList.size());


        for (int i = 0; i <myGame.storeList.size(); i++ ) {
            System.out.println("Amount of items in " + myGame.storeList.get(i).getName() + ": " + myGame.storeList.get(i).getInventory().size());
        }
        System.out.println();
        System.out.println("Total amount of items in the game: " + myGame.itemList.size());
    }

    public static void printItemOverview(GameDEUS myGame, int testItemNum) {
        System.out.println();
        System.out.println("Overview for nl.sogyo.jesper.itemfusion.Item: " + myGame.itemList.get(testItemNum).getName());
        System.out.println("Sold at " + myGame.itemList.get(testItemNum).getStoreList().size() + " store(s)");
        System.out.println("nl.sogyo.jesper.itemfusion.Fusion possibilities: " + myGame.itemList.get(testItemNum).getFusionList().size());
        System.out.println();

        for (int i = 0; i < myGame.itemList.get(testItemNum).getStoreList().size(); i++) {
            System.out.print(myGame.itemList.get(testItemNum).getPrices().get(i));
            System.out.print("  ~  ");
            System.out.println(myGame.itemList.get(testItemNum).getStoreList().get(i).getName());
        }
    }
}