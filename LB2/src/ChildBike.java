public class ChildBike extends Bicycle{
    private static int countOfChildBike; //static - инициализируется 1 раз и это общее поле для всех экземпляров класса.
    private String bColor;
    @Override
    public void speedDown() { System.out.println("Велосипед снижает скорость."); }
    @Override
    public void speedUP() {
        System.out.println("Велосипед набирает скорость.");
    }
    @Override
    public void wheelRemove() {
        System.out.println("1 колесо детского велосипеда убрали.");
    }
    @Override
    public void wheelAdd() {
        System.out.println("К детскому велосипеду прикрутили 1 колесо.");
    }
    public ChildBike(int cnt, float d, int speed, String color) {
        super(cnt, d, speed);
        bColor = color;
        countOfChildBike++;
        System.out.println("Создан объект Детский велосипед с текущими параметрами: колёс - "+cnt+"шт, диаметр колёс - "+d+"м, скорость - "
                +speed+"км/ч, цвет - "+color);
    }
    public ChildBike() {
        this(3, 0.3f, 0, "MetallGray");
    }
    public String getBColor() {
        return bColor;
    }
    public void setBColor(String new_color) {
        bColor = new_color;
    }
    public static int getCountOfChildBike() { return countOfChildBike; }

}



/*
public void getChildBike() {
        System.out.println("Это Детский велосипед с текущими параметрами: колёс - "+this.getCountOfWheel()+"шт, диаметр колёс - "+this.getDOfWheel()+"м, скорость - "+this.getSpeedOfBicycle()+"км/ч, цвет - "+this.getBColor());
    }
 */