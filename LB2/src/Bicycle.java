//абстракция
public abstract class Bicycle {
    private float dOfWheel;
    private int countOfWheel;
    private int speedOfBicycle;

    // модификаторы доступа - public private protected
    public Bicycle(int cnt, float d, int speed) {
        dOfWheel = d;
        countOfWheel = cnt;
        speedOfBicycle = speed;
    }

    // это не нужно, но всё же. Абстрактные методы - полиморфизм. Полиморфизм — свойство системы, позволяющее иметь множество реализаций одного интерфейса. Ничего непонятно.
    public abstract void speedUP();
    public abstract void speedDown();
    public abstract void wheelRemove();
    public abstract void wheelAdd();

    public int getCountOfWheel() { return countOfWheel; }
    public float getDOfWheel() { return dOfWheel; }
    public float getSpeedOfBicycle() { return speedOfBicycle; }
    public void setCountOfWheel(int new_cnt) { countOfWheel = new_cnt; }
    public void setDOfWheel(float new_d) { dOfWheel = new_d; }
    public void setSpeedOfBicycle(int new_speed) {speedOfBicycle = new_speed; }
    public void changeSpeed(int difSpeed) {
        int s = (int) this.getSpeedOfBicycle();
        if (difSpeed > 0) { this.speedUP();
        } else if (difSpeed < 0) { this.speedDown();
        } else { System.out.println("Велосипед не изменил своей скорости.");}
        this.setSpeedOfBicycle(s + difSpeed);
    }
}
/*
5 Базовый класс Велосипед

Горный велосипед,
Детский велосипед,
BMX
 */