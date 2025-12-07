package wastesort;
import java.util.Random;

public class WasteFactory {
    private final Random rand;

    public WasteFactory(long seed) {
        this.rand = new Random(seed);
    }


     //Create with a random seed (non-deterministic).

    public WasteFactory() {
        this.rand = new Random();
    }

    //Returns a random Waste instance with weighted category distribution.

    public Waste randomWaste() {
        int r = rand.nextInt(100);
        if (r < 35) return randomPlastic();
        else if (r < 60) return randomOrganic();
        else if (r < 80) return randomPaper();
        else return randomMetal();
    }

    private Plastic randomPlastic() {
        String[] types = {"PET", "HDPE", "LDPE", "PP", "OTHER"};
        String type = types[rand.nextInt(types.length)];
        double w = 0.01 + rand.nextDouble() * 0.5; // 10g - 510g
        boolean cleaned = rand.nextDouble() < 0.6;
        return new Plastic("Plastic Item", round(w), type, cleaned);
    }

    private Organic randomOrganic() {
        String[] names = {"Banana Peel", "Food Scraps", "Leaves", "Coffee Grounds"};
        String name = names[rand.nextInt(names.length)];
        double w = 0.02 + rand.nextDouble() * 1.5;
        int days = 7 + rand.nextInt(120);
        return new Organic(name, round(w), days);
    }

    private Paper randomPaper() {
        String[] names = {"Newspaper", "Cardboard", "Paper Ticket"};
        String name = names[rand.nextInt(names.length)];
        double w = 0.005 + rand.nextDouble() * 0.3;
        return new Paper(name, round(w));
    }

    private Metal randomMetal() {
        String[] metals = {"ALUMINUM", "STEEL", "COPPER"};
        String metalType = metals[rand.nextInt(metals.length)];
        String[] names = {"Can", "Bottle Cap", "Foil"};
        String name = names[rand.nextInt(names.length)];
        double w = 0.005 + rand.nextDouble() * 0.7;
        return new Metal(name, round(w), metalType);
    }

    private double round(double v) {
        return Math.round(v * 1000.0) / 1000.0;
    }
}
