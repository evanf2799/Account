import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Account {
    private String username;
    public Account(String requestedName) throws FileNotFoundException {
        if (isAvailable(requestedName)) {
            username = requestedName;
        }
        else {
            boolean stillTaken = true;
            long suffixNum = 1;
            while (stillTaken) {
                if (isAvailable(requestedName + suffixNum)) {
                    username = requestedName + suffixNum;
                    stillTaken = false;
                }
                else {
                    suffixNum++;
                }
            }
        }
    }
    public static boolean isAvailable(String str) throws FileNotFoundException {
        File f = new File("usernames.txt");
        java.util.Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            if (s.nextLine().equals(str)) {
                s.close();
                return false;
            }
        }
        s.close();
        return true;
    }
    public String getUsername() {
        return username;
    }
    public String getShortenedName() {
        String shortName = username;
        int hyphen = shortName.indexOf("-");
        while (hyphen > 0) {
            shortName = shortName.substring(0, hyphen - 1) + shortName.substring(hyphen + 1);
            hyphen = shortName.indexOf("-");
        }
        return shortName;
    }
}
