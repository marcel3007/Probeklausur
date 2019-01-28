package threads;

import model.Storage;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {

        Storage storage = new Storage(5);
        EinlagerungsThread einlagerungsThread = new EinlagerungsThread(storage, "Einlagerung");
        AuslagerungsThread auslagerungsThread = new AuslagerungsThread(storage, "Auslagerung");

        einlagerungsThread.start();
        auslagerungsThread.start();

        einlagerungsThread.join();
        auslagerungsThread.join();

        System.out.println(storage.toString());
    }
}
