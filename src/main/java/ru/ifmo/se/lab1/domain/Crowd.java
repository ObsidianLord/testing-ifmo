package ru.ifmo.se.lab1.domain;

public class Crowd {
    private Human[] people;

    public Crowd(Human[] people) {
        this.people = people;
    }

    public Applause applaud() {
        if (this.people.length > 100) {
            System.out.println("* Громкие аплодисменты *");
            return Applause.LOUD_APPLAUSE;
        } else if (this.people.length > 5) {
            System.out.println("* Аплодисменты *");
            return Applause.NORMAL_APPLAUSE;
        } else if (this.people.length > 0) {
            System.out.println("* Тихие аплодисменты *");
            return Applause.QUIET_APPLAUSE;
        } else {
            System.out.println("* Тишина *");
            return Applause.NO_APPLAUSE;
        }
    }
}
