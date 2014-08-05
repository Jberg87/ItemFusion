package nl.sogyo.jesper.itemfusion;

/**
 * Created by jvdberg on 25/04/2014.
 */
public class Fusion {
    private Item itemA, itemB;

    public Fusion(Item fusionItemA, Item fusionItemB) {
        itemA = fusionItemA;
        itemB = fusionItemB;
    }



    public int getLowestCost() {
        return getLowestCostItemA()+ getLowestCostItemB();
    }

    public int getLowestCostItemA() {
        if (itemA.isLocked()) {
            return itemA.getLowestItemCost();
        } else {
            return itemA.calculateLowestCost();
        }
    }

    public int getLowestCostItemB() {
        if (itemA.isLocked()) {
            return itemB.getLowestItemCost();
        } else {
            return itemB.calculateLowestCost();
        }
    }
}
