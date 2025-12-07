package wastesort;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


 //Core sorting logic. Keeps bins registered and statistics for processed items.

public class WasteSorter {
    private final Map<WasteCategory, WasteBin> bins = new HashMap<>();
    private int totalProcessed = 0;
    private int correctlySorted = 0;
    private int incorrectlySorted = 0;

    public void registerBin(WasteBin bin) {
        if (bin == null) throw new IllegalArgumentException("bin required");
        bins.put(bin.getCategory(), bin);
    }

    //Auto-sort watse to its correct category bin
    //Returns true if placed successfully and updates internal statistics
    public boolean autoSort(Waste w) {
        totalProcessed++;
        if (w == null) {
            incorrectlySorted++;
            return false;
        }
        WasteCategory correct = w.getCategory();
        WasteBin target = bins.get(correct);
        if (target == null) {
            // no bin for correct category -> incorrect
            incorrectlySorted++;
            return false;
        }
        boolean added = target.addWaste(w);
        if (added) correctlySorted++;
        else incorrectlySorted++; // e.g., bin full
        return added;
    }
    //Manual sort - User chooses a bin category to place the waste
    //Returns true if the waste is accepted in the chosen bin
    public boolean manualSort(Waste w, WasteCategory chosen) {
        totalProcessed++;
        if (w == null) {
            incorrectlySorted++;
            return false;
        }
        WasteBin chosenBin = bins.get(chosen);
        if (chosenBin == null) {
            incorrectlySorted++;
            return false;
        }
        boolean accepted = chosenBin.addWaste(w);
        if (accepted && chosen == w.getCategory()) correctlySorted++;
        else incorrectlySorted++;
        return accepted;
    }

    public int getTotalProcessed() { return totalProcessed; }
    public int getCorrectlySorted() { return correctlySorted; }
    public int getIncorrectlySorted() { return incorrectlySorted; }

    public Map<WasteCategory, WasteBin> getBins() {
        return Collections.unmodifiableMap(bins);
    }

    public double getAccuracyPercent() {
        if (totalProcessed == 0) return 0.0;
        return (correctlySorted * 100.0) / totalProcessed;
    }

}
