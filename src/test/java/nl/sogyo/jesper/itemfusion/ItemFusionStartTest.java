package nl.sogyo.jesper.itemfusion;

import junit.framework.TestCase;

public class ItemFusionStartTest extends TestCase {

    public void testAmount_of_Stores(){
        ItemFusionStart itemFusionStart = new ItemFusionStart();
        GameDEUS myDEUS = new GameDEUS();
        itemFusionStart.importGame(myDEUS);
        assertEquals(5, myDEUS.storeList.size());
    }

}