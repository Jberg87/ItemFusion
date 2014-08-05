package nl.sogyo.jesper.itemfusion;

/**
 * Created by jvdberg on 24/04/2014.
 */
public class ItemFusionStart {

    public static void main(String[] args) {
        GameDEUS myDEUS = new GameDEUS();
        importGame(myDEUS);

        // generate random item to check for
        int testItemNum = (int) (Math.random()*myDEUS.itemList.size());
        Item item = myDEUS.getItemList().get(testItemNum);
//        printItemOverview(myDEUS, testItemNum);

        System.out.println();
        System.out.println("Lowest price for " + item.getName() + ": ");
        item.calculateLowestCost();
//        bulkCheckPrices(myDEUS, testItemNum);

        System.out.println();
        int sumOfPurchases = item.getBestOption();
        System.out.println("-----------------------------");
        System.out.println("Sum: ~ " + sumOfPurchases);
        System.out.println();
        item.printTreeBestOptionB("");
    }

    private static void bulkCheckPrices(GameDEUS myDEUS, int testItemNum) {
        for (int i = 0; i < myDEUS.getItemList().size(); i++){
            Item item = myDEUS.getItemList().get(i);
            item.calculateLowestCost();
            item.unlock();
        }
        System.out.println("lowest price bulk fusion check: ~" + myDEUS.itemList.get(testItemNum).getLowestItemCost());

        for (int i = myDEUS.getItemList().size()-1; i >= 0 ; i--){
            Item item = myDEUS.getItemList().get(i);
            item.calculateLowestCost();
            item.unlock();
        }
        System.out.println("lowest price bulk fusion check: ~" + myDEUS.itemList.get(testItemNum).getLowestItemCost());

        for (int i = 0; i < myDEUS.getFusionList().size(); i++){
            Fusion fusion = myDEUS.getFusionList().get(i);
            fusion.getFusionCost();
            for(Item item:myDEUS.getItemList()) {
                item.unlock();
            }
        }
        System.out.println("lowest price bulk fusion check: ~" + myDEUS.itemList.get(testItemNum).getLowestItemCost());

        for (int i = myDEUS.getFusionList().size()-1; i >= 0 ; i--){
            Fusion fusion = myDEUS.getFusionList().get(i);
            fusion.getFusionCost();
            for(Item item:myDEUS.getItemList()) {
                item.unlock();
            }
        }
        System.out.println("lowest price bulk fusion check: ~" + myDEUS.itemList.get(testItemNum).getLowestItemCost());
    }

    public static void importGame(GameDEUS myGame) {
        myGame.fileReader();
//        printImport(myGame);
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
        System.out.println("Overview for item: " + myGame.itemList.get(testItemNum).getName());
        System.out.println("Sold at " + myGame.itemList.get(testItemNum).getStoreList().size() + " store(s)");
        System.out.println("Fusion possibilities: " + myGame.itemList.get(testItemNum).getFusionList().size());
        System.out.println();

        for (int i = 0; i < myGame.itemList.get(testItemNum).getStoreList().size(); i++) {
            System.out.print(myGame.itemList.get(testItemNum).getPrices().get(i));
            System.out.print("  ~  ");
            System.out.println(myGame.itemList.get(testItemNum).getStoreList().get(i).getName());
        }
    }
}