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

	// if there are computers that have not been restarted for over 10 days
        if (!usersAbove10Days.isEmpty()) {
            System.out.println("Users with an uptime above 10 days:");
            for (String user : usersAbove10Days) {
                System.out.println(user);
            }
	// if all computers are below 10 day threshold
        } else {
            System.out.println("No users found with an uptime above 10 days.");
        }
    }

    // method to get an excel file from the user
    private static String getExcelFilePathFromUser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Excel File");
	// allow both excel and csv based on the output from ConnectWise 
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xls", "xlsx", "csv");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        } else {
            System.out.println("No file selected. Exiting.");
            System.exit(0);
            return null; 
        }
    }

    // method to find the computers that have not been restarted for over 10 days
    private static List<String> filterUsersAbove10Days(String csvFile) {
    List<String> usersAbove10Days = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        String line;
	// to skip the header line
        boolean isFirstLine = true; 
        while ((line = br.readLine()) != null) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] data = line.split(",");
	    // need at least the computerName and uptime columns
            if (data.length < 2) {
                continue;
            }

            String computerName = data[0];
	    // if there are quotes then remove them
            String uptimeString = data[1].replace("\"", ""); 

            try {
                int uptime = Integer.parseInt(uptimeString);
		// calculate if the uptime is above 10 days
                if (uptime > 14400) {
		    int newUptime = uptime / 1440;
                    usersAbove10Days.add(computerName + ", " + newUptime + " days");
                }
            } catch (NumberFormatException e) {
                System.err.println("Invalid integer format for uptime: " + uptimeString);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return usersAbove10Days;
    }

}
