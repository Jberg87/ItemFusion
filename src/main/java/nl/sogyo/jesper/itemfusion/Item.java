package nl.sogyo.jesper.itemfusion;

import java.util.ArrayList;

/**
 * Created by jvdberg on 24/04/2014.
 */
public class Item {
    private String name;
    private ArrayList<Fusion> fusionList = new ArrayList<Fusion>();
    private ArrayList<Store> storeList = new ArrayList<Store>();
    private ArrayList<Integer> storePricesList = new ArrayList<Integer>();


    private boolean locked = false;
    private boolean bestBuyInStore = false;
    int lowestItemCost = 999999;
    private Store cheapestStore;
    private Fusion cheapestFusion;
    private int lowestPrice;

    public Item(Store store, int price, String name) {
        storeList.add(store);
        storePricesList.add(price);
        setName(name);
    }

    public Item(String name) {
        this.name = name;
    }


    public int calculateLowestCost() {
        locked = true;
//        System.out.println(name + " is locked");

        int lowestCostStorePrice;
        int lowestFusionCost;

        if (storePricesList.size() > 0) {
            lowestCostStorePrice = getLowestStorePrice();
            if (lowestCostStorePrice < lowestItemCost) {
                lowestItemCost = lowestCostStorePrice;
                bestBuyInStore = true;
            }
        }
        if (fusionList.size() > 0) {
            lowestFusionCost = getLowestFusionCost();
            if (lowestFusionCost < lowestItemCost) {
                lowestItemCost = lowestFusionCost;
                bestBuyInStore = false;
            }
        }
//        locked = false;
//        System.out.println("new lowest item cost (~" + lowestItemCost + ") for " + name );
//        System.out.println(name + " is unlocked");
        return lowestItemCost;
    }

    public int getLowestFusionCost() {
        int lowestFusionCost = 999999;
        for (Fusion fusion:fusionList) {
            if (fusion.getItemA().isLocked() | fusion.getItemB().isLocked()) continue;
            int fusionCost = fusion.getFusionCost();
            if (fusionCost < lowestFusionCost) {
                lowestFusionCost = fusionCost;
                cheapestFusion = fusion;
            }
        }
        return lowestFusionCost;
    }

    public int getLowestStorePrice() {
        int lowestStorePrice = 999999;
        for (int i=0; i < storePricesList.size(); i++){
            if (storePricesList.get(i) < lowestStorePrice ) lowestStorePrice = storePricesList.get(i);
            cheapestStore = storeList.get(i);
        }
        return lowestStorePrice;
    }

    public int getBestOption() {
        if (bestBuyInStore) {
            System.out.println(name + " - " + cheapestStore.getName() + " ~ " + lowestItemCost);
            return lowestItemCost;
        } else {
//            System.out.println(cheapestFusion.getItemA().getName() + "  x  " + cheapestFusion.getItemB().getName());
            return cheapestFusion.getItemA().getBestOption() + cheapestFusion.getItemB().getBestOption();
        }
    }



    public String printTreeBestOptionB(String lineA) {
        String arrow = " <-+-- ";
        String blankSpace = "";
        for (int i = 0; i < name.length(); i++) {
            blankSpace = blankSpace + " ";
        }
        String lineTemp = lineA;

        if (bestBuyInStore) {
            System.out.print(lineTemp + name);
            lineTemp = stripLine(lineTemp);   // afbreken lijn: last vertical is corner, other verticals are vertical, corner is nothing and arrow is vertical
//            System.out.println(lineTemp);

        } else {
            lineTemp = lineTemp + name + arrow;           // opbouwen line
            lineTemp = cheapestFusion.getItemA().printTreeBestOptionB(lineTemp);

            lineTemp = stripLine(lineTemp);
            cheapestFusion.getItemB().printTreeBestOptionB(lineTemp);
        }
        return lineTemp;
    }

    public String stripLine(String navigationLine) {
        String arrow = " <-+-- ";
        String vertical = "   |   ";
        String corner = "   +-- ";
        String navigationLine2 = navigationLine;

        if (navigationLine2.contains(corner))
            navigationLine2 = navigationLine2.replaceAll("  \\+--", "     ");

        boolean hasVertical = navigationLine2.contains(vertical);
        boolean hasArrow = navigationLine2.contains(arrow);
        boolean hasCorner = navigationLine2.contains(corner);

        if (hasVertical && !hasCorner && !hasArrow){
            System.out.print(navigationLine2);
        }

        if (navigationLine2.contains(vertical)) {
            int lastVertical = navigationLine2.lastIndexOf(vertical);
            navigationLine2 = navigationLine2.substring(0, lastVertical) + corner;
            navigationLine2 = navigationLine2.substring(0, lastVertical + 7);   // laatste nl.sogyo.jesper.itemfusion.Item er af knippen
        }

        if (navigationLine.contains(arrow))
            navigationLine2 = navigationLine2.replaceAll("<-\\+--", "  \\|  ");

        navigationLine2 = navigationLine2.replaceAll("\\p{L}", " ");
        navigationLine2 = navigationLine2.replaceAll("\\(", " ").replaceAll("\\)", " ");


        System.out.println();
        return navigationLine2;
    }

    public String stripLineBackup(String navigationLine) {
        String arrow = " <-+-- ";
        String vertical = "   |   ";
        String corner = "   +-- ";
        String navigationLine2 = navigationLine;



        if (navigationLine2.contains(corner))
            navigationLine2 = navigationLine2.replaceAll("  \\+--", "     ");


        boolean hasVertical = navigationLine2.contains(vertical);
        boolean hasArrow = navigationLine2.contains(arrow);
        boolean hasCorner = navigationLine2.contains(corner);

        if (hasVertical && !hasCorner && !hasArrow){
            System.out.print(navigationLine2);
        }


        if (navigationLine2.contains(vertical)) {
            int lastVertical = navigationLine2.lastIndexOf(vertical);
            navigationLine2 = navigationLine2.substring(0, lastVertical) + corner;
            navigationLine2 = navigationLine2.substring(0, lastVertical + 7);   // laatste nl.sogyo.jesper.itemfusion.Item er af knippen

        }

        if (navigationLine.contains(arrow))
            navigationLine2 = navigationLine2.replaceAll("<-\\+--", "  \\|  ");


        navigationLine2 = navigationLine2.replaceAll("\\p{L}", " ");
        navigationLine2 = navigationLine2.replaceAll("\\(\\)", " ");





        System.out.println();
        return navigationLine2;
    }


    public void addFusion(Fusion fusion){
        fusionList.add(fusion);
    }





    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public void addStoreAndPrice(Store store, int price) {
        storeList.add(store);
        storePricesList.add(price);
    }


    public ArrayList<Store> getStoreList() {
        return storeList;
    }

    public ArrayList<Integer> getPrices() {
        return storePricesList;
    }

    public int getLowestItemCost() {
        return lowestItemCost;
    }


    public ArrayList<Fusion> getFusionList() {
        return fusionList;
    }

    public boolean isLocked() {
        return locked;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void unlock() {
        locked=false;
    }
}
