package wastesort;
public class Metal extends Waste  {
    private final String metalType;

    public Metal(String name, double weightKg, String metalType) {
        super(name, weightKg, metalType, 0);
        this.metalType = metalType;
    }
    public String getMetaltype() { return metalType; }

    @Override
    public WasteCategory getCategory () {
        return WasteCategory.METAL;
    }
    @Override
    public boolean isRecyclable() {
        //Metals are generally recyclable
        return true;
    }

    @Override
    public String getSortHint() {
        return "Place in METAL bin. Remove non-metal parts if possible.";
    }
}
