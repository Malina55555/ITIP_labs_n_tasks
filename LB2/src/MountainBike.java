public class MountainBike extends Bicycle {
   private int cntOfSpeeds;
    @Override
    public void speedDown() {
        System.out.println("Велосипед снижает скорость.");
    }
    @Override
    public void speedUP() {
        System.out.println("Велосипед набирает скорость.");
    }
    @Override
    public void wheelRemove() { System.out.println("1 колесо горного велосипеда убрали."); }
    @Override
    public void wheelAdd() {
        System.out.println("К горному велосипеду прикрутили 1 колесо.");
    }
    public MountainBike(int cnt, float d, int speed, int cntSpeeds) {
        super(cnt, d, speed);
        cntOfSpeeds = cntSpeeds;
        System.out.println("Создан объект Горный велосипед с текущими параметрами: колёс - "+cnt+"шт, диаметр колёс - "+d+
                "м, скорость - "+speed+"км/ч, количество скоростей - "+cntSpeeds);
    }
    public MountainBike() { this(2, 0.66f, 0, 10); }
    public int getCntOfSpeeds() { return cntOfSpeeds; }
    public void setCntOfSpeeds(int new_cnt_speeds) { cntOfSpeeds = new_cnt_speeds; }
}



/*
public void getMountainBike() {
        System.out.println("Это горный велосипед с текущими параметрами: колёс - "+this.getCountOfWheel()+"шт, диаметр колёс - "+this.getDOfWheel()+"м, скорость - "+this.getSpeedOfBicycle()+"км/ч, количество скоростей - "+this.getCntOfSpeeds());
    }
 */
