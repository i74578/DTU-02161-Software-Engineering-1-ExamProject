package timeCat.Presentation;
import io.cucumber.messages.types.TableCell;
import timeCat.Domain.Project;
import timeCat.Domain.Tabelify;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@author  Benjamin Fríðberg - s224347
public class view {
    String HorizontalLine = "#".repeat(100) + "\n";
    private static final int cliWidth = 100;

    public static void view(){

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
    public void showMainMenu(ArrayList<commandOption> options){
        printTable(options,true);
        System.out.print("\n Choose option:");
    }

    //@author  Benjamin Fríðberg - s224347
    public void showProjects(ArrayList<Project> projects){
        printTableHeader("Projects");
        printTable(projects,false);
    }

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
    public void printTableHeader(String headerText){
        print(HorizontalLine + centerStringInTableRecord(headerText));
    }

    //@author  Benjamin Fríðberg - s224347
    public String centerStringInTableRecord(String stringToCenter){
        int spacesNeedToFillWidth = cliWidth-stringToCenter.length() - 2;
        String leftSpacePadding = " ".repeat(spacesNeedToFillWidth / 2);
        String rightSpacePadding = " ".repeat((spacesNeedToFillWidth / 2)+(stringToCenter.length() % 2));
        return "#"+leftSpacePadding+stringToCenter+rightSpacePadding+"#";
    }

    //@author  Benjamin Fríðberg - s224347
    public void printTable(ArrayList<? extends Tabelify> objectToPrint, boolean printIndex){
        String table = HorizontalLine;
        if(objectToPrint.size() == 0){
            table += centerStringInTableRecord("Empty")+"\n";
        }
        for (int i = 0;i<objectToPrint.size();i++){
            String[] recordProperties = objectToPrint.get(i).getMainProperties();
            String recordText = " ";
            if(printIndex){
                recordText += (i+1) + ". ";
            }
            recordText += String.join(" - ",recordProperties);
            table += "#" + recordText + " ".repeat(cliWidth-2-recordText.length())+ "#\n";
        }
        table += HorizontalLine;
        print(table);
    }
}
