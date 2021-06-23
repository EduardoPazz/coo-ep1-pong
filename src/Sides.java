public class Sides {

    private interface SidesLogic {
        double calc(double center, double measure);    
    }

    private interface SideCalculation {
        double calc(SidesLogic logic, double center, double measure);
    }

    private static SidesLogic greaterValueLogic =
        (center, measure) -> center + measure / 2;

    private static SidesLogic lowerValueLogic =
        (center, measure) -> center - measure / 2;

    private static SideCalculation calculation =
        (logic, center, measure) -> logic.calc(center, measure);

    public static double getSide(Side side, double center, double measure) {
        SidesLogic logic = (side == Side.LEFT || side == Side.TOP) 
            ? lowerValueLogic : greaterValueLogic;

        return calculation.calc(logic, center, measure);
    }

}
