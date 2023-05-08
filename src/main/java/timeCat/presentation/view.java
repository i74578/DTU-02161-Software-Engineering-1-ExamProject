package timeCat.presentation;

import timeCat.domain.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//@author  Benjamin Fríðberg - s224347
public class view {
    private final static int cliWidth = 150;
    private final static String HorizontalLine = "#".repeat(cliWidth);

    //@author  Benjamin Fríðberg - s224347
    public void showWelcomeScreen() {
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
    public void clearConsole() {
        print("\n".repeat(20));
    }

    //@author  Benjamin Fríðberg - s224347
    public void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    //@author  Benjamin Fríðberg - s224347
    public void printError(String errorMessage) {
        System.out.println("ERROR - " + errorMessage);
    }

    //@author  Benjamin Fríðberg - s224347
    public void showMainMenu(ArrayList<Feature> features) {
        printTable(features, true);
        System.out.print("\n Choose option:");
    }

    //@author  Benjamin Fríðberg - s224347
    public void printTableWithHeader(ArrayList<?> tableElements, boolean printIndex, String tableHeader) {
        print(HorizontalLine);
        print(centerString(tableHeader));
        printTable(tableElements, printIndex);
    }

    //@author  Benjamin Fríðberg - s224347
    public void printTable(ArrayList<?> tableContent, boolean printIndex) {
        print(HorizontalLine);
        if (tableContent.size() == 0) {
            print(centerString("Empty"));
        }
        for (int tableRow = 0; tableRow < tableContent.size(); tableRow++) {
            String[] recordProperties = extractMainProperties(tableContent.get(tableRow));
            String recordText = " ";
            if (printIndex) {
                recordText += (tableRow + 1) + ". ";
            }
            recordText += String.join(" | ", recordProperties);
            if (cliWidth - 2 - recordText.length() >= 0) {
                print("#" + recordText + " ".repeat(cliWidth - 2 - recordText.length()) + "#");
            } else {
                print("#" + recordText + "#");
            }
        }
        print(HorizontalLine);
    }

    //@author  Benjamin Fríðberg - s224347
    public String centerString(String stringToCenter) {
        int spacesNeedToFillWidth = cliWidth - stringToCenter.length() - 2;
        String leftSpacePadding = " ".repeat(spacesNeedToFillWidth / 2);
        String rightSpacePadding = " ".repeat((spacesNeedToFillWidth / 2) + (stringToCenter.length() % 2));
        return "#" + leftSpacePadding + stringToCenter + rightSpacePadding + "#";
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] extractMainProperties(Object obj) {
        if (obj instanceof Project) {
            Project project = (Project) obj;
            String projectType = "";
            if (obj instanceof CostumerProject) {
                projectType = "Costumer Project";
            }
            if (obj instanceof InternalProject) {
                projectType = "Internal Project";
            }
            Employee pm = project.getPM();
            String pmInitials = "No PM";
            if(pm != null){
                pmInitials = pm.getInitials();
            }
            return new String[]{project.getID(), project.getName(), projectType, pmInitials};
        }
        if (obj instanceof Activity) {
            Activity activity = (Activity) obj;
            return new String[]{String.valueOf(activity.getActivityID()), activity.getName()};
        }
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            return new String[]{employee.getInitials()};
        }
        if (obj instanceof ReportEntry) {
            ReportEntry reportEntry = (ReportEntry) obj;
            DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone(reportEntry.getDate().getTimeZone());
            return new String[]{reportEntry.getProjectName(),reportEntry.getActivityName(),sdf.format(reportEntry.getDate().getTime()), String.valueOf(reportEntry.getHours())};
        }
        if (obj instanceof Feature) {
            Feature feature = (Feature) obj;
            return new String[]{feature.getName(),feature.getDescription()};
        }
        return null;
    }
}