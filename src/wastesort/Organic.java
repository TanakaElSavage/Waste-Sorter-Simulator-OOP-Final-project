package wastesort;

public class Organic extends Waste {
    public Organic(String name, double weightKg, int decompositionDays) {
        super(name, weightKg, "organic", decompositionDays);
    }
    @Override
    public WasteCategory getCategory() {
        return WasteCategory.ORGANIC;
    }
    @Override
    public boolean isRecyclable() {
        // Organic wastes are compostable, not recycled in normal recyclables
        return false;
    }
    @Override
    public String getSortHint() {
        return "Place in ORGANIC  bin.";
    }
}
