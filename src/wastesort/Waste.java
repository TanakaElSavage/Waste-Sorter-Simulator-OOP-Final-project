package wastesort;
import java.util.Objects;
import java.util.UUID;
public abstract class Waste {
    private final String id;
    private final String name ;
    private final double weightKg;
    private final String materialType;
    private final int decompositionDays;

    //Constructors
    public Waste (  String name, double weightKg, /*wastesort.WasteCategory category*/ String materialType, int decompositionDays){
        if (name==null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty!!!");
        if (weightKg<0) throw new IllegalArgumentException("Weight cannot be negative!!!");
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.weightKg = weightKg;
        this.materialType = materialType;
        this.decompositionDays = decompositionDays;
    }

    //Getter methods
    public String getName() { return name; }
    public double getWeightKg() { return weightKg; }
    public int getDecompositionDays() { return decompositionDays; }
    public String getId() { return id; }
    public String getMaterialType() { return materialType; }

    //Abstract Classes
    public abstract WasteCategory getCategory();
    public abstract boolean isRecyclable ();

    public String getSortHint() {
        return "Place in " + getCategory() + " bin";
    }

    public String getDetailedInfo() {
        return String.format("%s (id=%s) - %.3f kg, material=%s, decomp=%d days",
                name, id, weightKg, materialType == null ? "unknown" : materialType, decompositionDays);
    }
    @Override
    public String toString() {
        return getDetailedInfo();
    }

    @Override
    public boolean equals (Object o) {
        if(this ==o) return false;
        if (!(o instanceof Waste)) return false;
        Waste waste = (Waste) o;
        return Objects.equals(id,waste.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
//Why are there no setter methods
