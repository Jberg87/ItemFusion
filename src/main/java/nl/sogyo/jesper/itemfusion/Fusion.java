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
    private boolean checked = false;



    public int getFusionCost() {
        checked =true;
        return getLowestCostItemA()+ getLowestCostItemB();
    }

    public int getLowestCostItemA() {
        return itemA.calculateLowestCost();
    }

    public int getLowestCostItemB() {
        return itemB.calculateLowestCost();
    }

    public Item getItemA() {
        return itemA;
    }

    public Item getItemB() {
        return itemB;
    }

    public boolean isChecked() {
        return checked;
    }


}
