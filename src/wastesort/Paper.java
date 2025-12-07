package wastesort;

public class Paper extends Waste {
    public Paper(String name, double weightKg) {
        // set decompositionDays as an example (paper may take months)
        super(name, weightKg, "paper", 365);
    }

    @Override
    public WasteCategory getCategory() {
        return WasteCategory.PAPER;
    }
    @Override
    public boolean isRecyclable() {
        return true;
    }
    @Override
    public String getSortHint() {
        return "Place in PAPER bin. Remove food-contaminated paper.";
    }
}
