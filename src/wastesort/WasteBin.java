package wastesort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WasteBin {
    private final WasteCategory category;
    private final double capacityKg;
    private double currentLoadKg =0.0;
    private final List<Waste> contents = new ArrayList<>();

    public WasteBin(WasteCategory category, double capacityKg) {
        if (category == null) throw new IllegalArgumentException("category required");
        if (capacityKg <= 0) throw new IllegalArgumentException("capacity must be greater than 0");
        this.category = category;
        this.capacityKg = capacityKg;
    }
    public WasteCategory getCategory() { return category; }
    public double getCapacityKg() { return capacityKg; }
    public double getCurrentLoadKg() { return currentLoadKg; }

    //Note: Category must match AND adding the weight must not exceed capacity
    public boolean canAccept(Waste w) {
        if (w == null) return false;
        return w.getCategory() == category && (currentLoadKg + w.getWeightKg() <= capacityKg + 1e-9);
    }
    //Add more waste if possible and returns true if successful
    public boolean addWaste(Waste w) {
        if (!canAccept(w)) return false;
        contents.add(w);
        currentLoadKg += w.getWeightKg();
        return true;
    }

    //Empties the bin and returns the previos contents
    public List<Waste> emptyBin() {
        List<Waste> snapshot = new ArrayList<>(contents);
        contents.clear();
        currentLoadKg = 0.0;
        return snapshot;
    }
    public double getFillPercentage() {
        return (currentLoadKg / capacityKg) * 100.0;
    }

    public List<Waste> getContents() {
        return Collections.unmodifiableList(contents);
    }

    public String getStatus() {
        return String.format("%s: %.3f / %.3f kg (%.1f%%)", category, currentLoadKg, capacityKg, getFillPercentage());
    }
}
