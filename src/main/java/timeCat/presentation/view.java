package timeCat.presentation;
import timeCat.domain.Tabelify;

import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class view {
    private final static int cliWidth = 150;
    private final static String HorizontalLine = "#".repeat(cliWidth);

    //@author  Benjamin Fríðberg - s224347
    public void showWelcomeScreen(){
        //Welcome screen ASCII art source: https://patorjk.com/software/taag/
        String welcomeScreen =
            " __      __          ___                                             __               __                            ____              __      \n" +
            "/\\ \\  __/\\ \\        /\\_ \\                                           /\\ \\__           /\\ \\__  __                    /\\  _`\\           /\\ \\__   \n" +
            "\\ \\ \\/\\ \\ \\ \\     __\\//\\ \\     ___    ___     ___ ___      __       \\ \\ ,_\\   ___    \\ \\ ,_\\/\\_\\    ___ ___      __\\ \\ \\/\\_\\     __  \\ \\ ,_\\  \n" +
            " \\ \\ \\ \\ \\ \\ \\  /'__`\\\\ \\ \\   /'___\\ / __`\\ /' __` __`\\  /'__`\\      \\ \\ \\/  / __`\\   \\ \\ \\/\\/\\ \\ /' __` __`\\  /'__`\\ \\ \\/_/_  /'__`\\ \\ \\ \\/  \n" +
            "  \\ \\ \\_/ \\_\\ \\/\\  __/ \\_\\ \\_/\\ \\__//\\ \\L\\ \\/\\ \\/\\ \\/\\ \\/\\  __/       \\ \\ \\_/\\ \\L\\ \\   \\ \\ \\_\\ \\ \\/\\ \\/\\ \\/\\ \\/\\  __/\\ \\ \\L\\ \\/\\ \\L\\.\\_\\ \\ \\_ \n" +
            "   \\ `\\___x___/\\ \\____\\/\\____\\ \\____\\ \\____/\\ \\_\\ \\_\\ \\_\\ \\____\\       \\ \\__\\ \\____/    \\ \\__\\\\ \\_\\ \\_\\ \\_\\ \\_\\ \\____\\\\ \\____/\\ \\__/.\\_\\\\ \\__\\\n" +
            "    '\\/__//__/  \\/____/\\/____/\\/____/\\/___/  \\/_/\\/_/\\/_/\\/____/        \\/__/\\/___/      \\/__/ \\/_/\\/_/\\/_/\\/_/\\/____/ \\/___/  \\/__/\\/_/ \\/__/\n\n";
        System.out.println(welcomeScreen);
    }

    //@author  Benjamin Fríðberg - s224347
    public void clearConsole(){
        print("\n".repeat(20));
    }

    //@author  Benjamin Fríðberg - s224347
    public void print(String stringToPrint){
        System.out.println(stringToPrint);
    }

    //@author  Benjamin Fríðberg - s224347
    public void printError(String errorMessage){
        System.out.println("ERROR - " + errorMessage);
    }

    //@author  Benjamin Fríðberg - s224347
    public void showMainMenu(ArrayList<feature> features){
        printTable(features,true);
        System.out.print("\n Choose option:");
    }

    //@author  Benjamin Fríðberg - s224347
    public void printTableWithHeader(ArrayList<? extends Tabelify> tableElements,boolean printIndex,String tableHeader){
        print(HorizontalLine);
        print(centerString(tableHeader));
        printTable(tableElements,printIndex);
    }

    //@author  Benjamin Fríðberg - s224347
    public void printTable(ArrayList<? extends Tabelify> tableContent, boolean printIndex){
        print(HorizontalLine);
        if(tableContent.size() == 0){
            print(centerString("Empty"));
        }
        for (int tableRow = 0;tableRow<tableContent.size();tableRow++){
            String[] recordProperties = tableContent.get(tableRow).getPropertiesForTable();
            String recordText = " ";
            if(printIndex){
                recordText += (tableRow+1) + ". ";
            }
            recordText += String.join(" | ",recordProperties);
            if(cliWidth-2-recordText.length() >= 0) {
                print("#" + recordText + " ".repeat(cliWidth - 2 - recordText.length()) + "#");
            }
            else{
                print("#" + recordText + "#");
            }
        }
        print(HorizontalLine);
    }

    //@author  Benjamin Fríðberg - s224347
    public String centerString(String stringToCenter){
        int spacesNeedToFillWidth = cliWidth-stringToCenter.length() - 2;
        String leftSpacePadding = " ".repeat(spacesNeedToFillWidth / 2);
        String rightSpacePadding = " ".repeat((spacesNeedToFillWidth / 2)+(stringToCenter.length() % 2));
        return "#"+leftSpacePadding+stringToCenter+rightSpacePadding+"#";
    }
}
