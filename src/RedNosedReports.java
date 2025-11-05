import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {
    public static void main(String[] args) throws IOException {
        List<List<Integer>> reports = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    List<Integer> levels = new ArrayList<>();
                    for (String s : line.trim().split("\\s+")) {

                        levels.add(Integer.parseInt(s));

                    }

                    reports.add(levels);
                }
            }
        }

        int safeCount = 0;
        for (List<Integer> levels : reports) {
            if ( isSafe(levels) ) {
                safeCount++;
            }
        }

        System.out.println("Nbr de rapports sûrs : " +safeCount);
    }

    private static boolean isSafe(List<Integer> levels) {
        if (levels.size() < 2) return true;

        int firstDiff = levels.get(1) - levels.get(0);
        if (firstDiff == 0) return false;
        boolean increasing = firstDiff > 0;

        for (int i = 1; i < levels.size(); i++) {
            int diff = levels.get(i) - levels.get(i - 1);

            // must be in the same direction
            if ((increasing && diff <= 0) || (!increasing && diff >= 0)) {
                return false;
            }

            // the difference must >= 1 and <= 3
            int absDiff = Math.abs(diff);
            if (absDiff < 1 || absDiff > 3) {
                return false;
            }
        }
        return true;
    }
}
