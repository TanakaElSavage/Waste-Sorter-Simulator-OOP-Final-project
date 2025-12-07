package wastesort;

public class Plastic extends Waste{
    private final String plasticType;
    private final boolean cleaned;

    public Plastic(String name, double weightKg, String plasticType, boolean cleaned){
        super(name, weightKg, plasticType, 0);
        this.plasticType = plasticType;
        this.cleaned = cleaned;
    }

    public String getPlasticType() { return plasticType; }
    public boolean isCleaned () { return cleaned; }

    //Methods Overriding
    @Override
    public WasteCategory getCategory(){
        return WasteCategory.PLASTIC;
    };
    public boolean isRecyclable(){
        // Rule: only some plastic types are recyclable and must be cleaned
        if (!cleaned) return false;
        if (plasticType == null) return false;
        String t = plasticType.trim().toUpperCase();
        return t.equals("PET") || t.equals("HDPE") || t.equals("PP");
    };
    public  String getSortHint() {
        if (cleaned) return "Rinse & place in PLASTIC bin (type: " + plasticType + ").";
        return "Rinse before recycling. Place in PLASTIC bin (type: " + plasticType + ").";

    }
}
