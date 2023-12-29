public class Main {
    public static void main(String[] args) {
        BMX bmx1 = new BMX();
        System.out.println("Это ВМХ с параметрами: колёс - "+bmx1.getCountOfWheel()+
                " диаметр колеса - "+bmx1.getDOfWheel()+" скорость - "+bmx1.getSpeedOfBicycle()+
                " направление - "+bmx1.getDirection());
        System.out.println(bmx1.getDOfWheel());
        ChildBike cb1 = new ChildBike(3, 0.35f, 2, "Yellow");
        System.out.println("Это Детский велосипед с параметрами: колёс - "+cb1.getCountOfWheel()+
                " диаметр колеса - "+cb1.getDOfWheel()+" скорость - "+cb1.getSpeedOfBicycle()+
                " цвет - "+cb1.getBColor());
        cb1.setBColor("No Yellow");
        cb1.wheelRemove();
        System.out.println("Current color of cb1 - "+cb1.getBColor());
        MountainBike mb1 = new MountainBike();
        mb1.changeSpeed(7);
        System.out.println("Current speed of mb1 - "+mb1.getSpeedOfBicycle());
        mb1.wheelAdd();
        System.out.println("Count of ChildBike objects now:"+ChildBike.getCountOfChildBike());
        ChildBike cb2 = new ChildBike();
        ChildBike cb3 = new ChildBike();
        System.out.println("Count of ChildBike objects now:"+ChildBike.getCountOfChildBike());
    }
}


