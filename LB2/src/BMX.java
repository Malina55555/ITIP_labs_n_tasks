/*
   •	Мы можем переопределить метод суперкласса в классе наследнике. Однако мы всегда должны аннотировать переопределенный метод аннотацией @Override.
   Компилятор будет знать, что мы переопределяем метод, и если что-то изменится в методе суперкласса, то мы получим ошибку компиляции,
   а не нежелательные результаты во время выполнения.
    */
// но эти ф-ии присутствуют и в других классах - поэтому перегрузка
public class BMX extends Bicycle {
    private String direction; //направление/использование: рейсинг(racing), флетленд(flatland), верт(vert), дёрт(dirt), стрит(street) и парк(park).
    @Override // переопределение
    public void speedDown() { System.out.println("Велосипед снижает скорость."); }
    @Override
    public void speedUP() {
        System.out.println("Велосипед набирает скорость.");
    }
    @Override
    public void wheelRemove() {
        System.out.println("1 колесо велосипеда ВМХ убрали.");
    }
    @Override
    public void wheelAdd() {
        System.out.println("К ВМХ прикрутили 1 колесо.");
    }
    public BMX(int cnt, float d, int speed, String dir) {
        super(cnt, d, speed);
        direction = dir;
        System.out.println("Создан объект ВМХ с текущими параметрами: колёс - "+cnt+"шт, диаметр колёс - "+d+
                "м, скорость - "+speed+"км/ч, направление - "+dir);
    }
    public BMX() { this(2, 0.51f, 0, "racing"); }
    public String getDirection() { return direction; }
    public void setDirection(String new_dir) { direction = new_dir; }
}


/*
 public String getBMX() {
        System.out.println(getCountOfWheel());
        System.out.println(this.getCountOfWheel());
        int q1 = this.getCountOfWheel();
        return ("Это ВМХ с текущими параметрами: колёс - "+q1+"шт, диаметр колёс - "+this.getDOfWheel()+"м, скорость - "+this.getSpeedOfBicycle()+"км/ч, направление - "+direction);
    }
 */
