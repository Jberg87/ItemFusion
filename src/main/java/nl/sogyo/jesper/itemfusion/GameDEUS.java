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
    ArrayList<Fusion> fusionList = new ArrayList<Fusion>();

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
                else if (lineTemp.contains("~")) {
                    handleItemLine(lineTemp);
                }
                else if (lineTemp.contains("=")) {
                    handleFusionLine(lineTemp);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File could not be found");
        }
    }


    public File getFile() {
        File fileComplete = new File("C:\\Users\\jvdberg\\Downloads\\Fusion.txt");
        System.out.println("File is loaded from :" + fileComplete.toString());
        return fileComplete;
    }

    public void handleStoreLine(String lineTemp) {
        String[] lTSplit = lineTemp.split("Items");    // String before " Items" is the store's name
        storeList.add(new Store(lTSplit[0].trim()));
    }

    public void handleItemLine(String lineTemp) {
        Item item;
        if (lineTemp.contains("*") | storeList.size() == 1) {
            item = new Item(storeList.get(storeList.size()-1), readItemPrice(lineTemp), readItemName(lineTemp));
            storeList.get(storeList.size()-1).addToInventory(item);
            itemList.add(item);
        } else {
            item = getItemByName(readItemName(lineTemp));
            item.addStoreAndPrice(storeList.get(storeList.size() - 1), readItemPrice(lineTemp));
            storeList.get(storeList.size()-1).addToInventory(item);
        }
//        System.out.println(item.getName() + " is te koop in " + item.getStoreList().size() + " winkels");
    }

    private Item getItemByName(String name) {
        for (Item item:itemList) {
            if (name.equals(item.getName())) return item;
        }
        return null;
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
        String[] lineSplit = lineTemp.split("=");
        String[] fusionItems = lineSplit[0].split("\\+");
        Item fusionItemA = getItemByName(fusionItems[0].trim());
        Item fusionItemB = getItemByName(fusionItems[1].trim());
        Item resultItem = getItemByName(lineSplit[1].trim());
        Fusion fusion = new Fusion(fusionItemA, fusionItemB);
        fusionList.add(fusion);
        if (resultItem == null) {
            resultItem = new Item(lineSplit[1].trim());
            resultItem.addFusion(fusion);
            itemList.add(resultItem);
        } else {
            resultItem.addFusion(fusion);
        }
    }

    public ArrayList<Fusion> getFusionList() {
        return fusionList;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }
}