import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class uptimeCalc {
    public static void main(String[] args) {
        String csvFile = getExcelFilePathFromUser();
        List<String> usersAbove10Days = filterUsersAbove10Days(csvFile);

        if (!usersAbove10Days.isEmpty()) {
            System.out.println("Users with an uptime above 10 days:");
            for (String user : usersAbove10Days) {
                System.out.println(user);
            }
        } else {
            System.out.println("No users found with an uptime above 10 days.");
        }
    }

    private static String getExcelFilePathFromUser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Excel File");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "csv");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            System.out.println("No file selected. Exiting.");
            System.exit(0);
            return null; // This line will never be reached, but Java requires a return statement here.
        }
    }

    private static List<String> filterUsersAbove10Days(String csvFile) {
    List<String> usersAbove10Days = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
        boolean isFirstLine = true; // Skip the header row if present
        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] data = line.split(",");
            if (data.length < 2) {
                // Make sure the data array has at least two elements: computerName and uptime.
                continue;
            }

            String computerName = data[0];
            String uptimeString = data[1].replace("\"", ""); // Remove double quotes if present.

            try {
                int uptime = Integer.parseInt(uptimeString);
                if (uptime > 14400) {
		    int newUptime = uptime / 1440;
                    usersAbove10Days.add(computerName + ", " + newUptime + " days");
                }
            } catch (NumberFormatException e) {
                // Handle invalid integer format.
                System.err.println("Invalid integer format for uptime: " + uptimeString);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return usersAbove10Days;
    }

}
