public class CourierGenerator {

    public static Courier getDefault() {
        return new Courier("login8585", "12345", "Vasya");
    }
    public static Courier getWithoutLogin() {
        return new Courier("", "12345", "Vasya");
    }

    public static Courier getWithoutPassword() {
        return new Courier("login9991234", "", "Vasya");
    }

    public static Courier getNonExistent() {
        return  new Courier("rtwhnrb1251", "dg254g54", "Vasya");
    }

}
