public class OrderGenerator {
    static String[] colors;
    public static Order getDefault () {
        return new Order("Иван",
                "Иванов",
                "Неизвестная ул., 6",
                10,
                "+798153201895",
                5,
                "2022-10-10",
                "Позвоните, как подъедите",
                colors);
    }
}
