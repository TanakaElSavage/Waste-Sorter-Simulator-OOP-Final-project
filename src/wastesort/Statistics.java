package wastesort;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Statistics {
    private final WasteSorter sorter;

    public Statistics(WasteSorter sorter) {
        this.sorter = sorter;
    }

    public void printSummary() {
        System.out.println("===== Simulation Summary =====");
        System.out.println("Total processed: " + sorter.getTotalProcessed());
        System.out.println("Correctly sorted: " + sorter.getCorrectlySorted());
        System.out.println("Incorrectly sorted: " + sorter.getIncorrectlySorted());
        System.out.printf("Accuracy: %.2f%%\n", sorter.getAccuracyPercent());
        System.out.println();
        System.out.println("Bin statuses:");
        for (Map.Entry<WasteCategory, WasteBin> e : sorter.getBins().entrySet()) {
            System.out.println("  " + e.getValue().getStatus());
        }
        System.out.println("==============================");
    }

    //Save a simple CSV summarizing category loads and counts.
    public void saveCsv(Path out) throws IOException {
        try (BufferedWriter bw = Files.newBufferedWriter(out)) {
            bw.write("category,currentKg,capacityKg,fillPercent\n");
            for (Map.Entry<WasteCategory, WasteBin> e : sorter.getBins().entrySet()) {
                WasteBin b = e.getValue();
                bw.write(String.format("%s,%.3f,%.3f,%.1f\n",
                        b.getCategory().name(), b.getCurrentLoadKg(), b.getCapacityKg(), b.getFillPercentage()));
            }
        }
    }
}
