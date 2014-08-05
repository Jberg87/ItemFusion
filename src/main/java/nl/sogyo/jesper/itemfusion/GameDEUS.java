package nl.sogyo.jesper.itemfusion;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jvdberg on 25/04/2014.
 */
public class GameDEUS {

    ArrayList<Store> storeList = new ArrayList<Store>();
    ArrayList<Item> itemList = new ArrayList<Item>();

    public void fileReader() {
        try {
            File fileComplete = getFile();
            Scanner lineRead = new Scanner(fileComplete);
            while (lineRead.hasNextLine() == true) {
                String lineTemp = lineRead.nextLine();
                // "Items:" = storeLine, "~" = itemLine, "=" = fusionLine
                if (lineTemp.contains(" Items:")) {
                    handleStoreLine(lineTemp);
                }
                else if (lineTemp.contains("~") && storeList.get(storeList.size()-1) != null) {
                    handleItemLine(lineTemp, storeList.get(storeList.size() - 1));
                }
                else if (lineTemp.contains("=") && storeList.get(storeList.size()-1) != null) {
                    handleFusionLine(lineTemp);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File could not be found");
        }
    }


    public File getFile() {
        File fileComplete = new File("C:\\Users\\jvdberg\\Downloads\\nl.sogyo.jesper.itemfusion.Fusion.txt");
        System.out.println("File is loaded from :" + fileComplete.toString());
        return fileComplete;
    }

    public void handleStoreLine(String lineTemp) {
        String[] lTSplit = lineTemp.split("Items");    // String before " Items" is the store's name
        storeList.add(new Store(lTSplit[0].trim()));
    }

    public void handleItemLine(String lineTemp, Store storeTemp) {
        if (lineTemp.contains("*")) {
            Item itemTemp = new Item(storeTemp, readItemPrice(lineTemp), readItemName(lineTemp));
            storeList.get(storeList.size()-1).addToInventory(itemTemp);
            itemList.add(itemTemp);
        } else {
            Item item = getItemByName(readItemName(lineTemp));
//            if (item == null) System.out.println("handleItemLine(): item niet gevonden in lijst");
            item.addStoreAndPrice(storeList.get(storeList.size()-1), readItemPrice(lineTemp));
            storeList.get(storeList.size()-1).addToInventory(item);
        }
    }

    private Item getItemByName(String name) {
        for (int i = 0; i < itemList.size(); i++) {
            if (name.equals(itemList.get(i))) return itemList.get(i);
        }
        Item newItem = new Item(name);
        itemList.add(newItem);
        return newItem;
    }

    public String readItemName(String lineTemp) {
        String lineTemp2 = lineTemp.replace("*","");
        String[] lTSplit2 = lineTemp2.split("~");
        String name = lTSplit2[0].trim();
        return name;
    }

    public int readItemPrice(String lineTemp) {
        String[] lTSplit = lineTemp.split("~");
        Scanner itemScanner = new Scanner(lTSplit[lTSplit.length - 1]);
        int price = itemScanner.nextInt();
        return price;
    }

    public void handleFusionLine(String lineTemp) {
        String[] lTSplit = lineTemp.split("=\\+");
//        String[] lTSplitFusions = lTSplit[0].split("\\+");
        Item fusionItemA = getItemByName(lTSplit[0].trim());
        Item fusionItemB = getItemByName(lTSplit[1].trim());
        Item resultItem = getItemByName(lTSplit[2].trim());
        resultItem.addFusion(new Fusion(fusionItemA, fusionItemB));
    }
}