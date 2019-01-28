package threads;

import model.Storage;

public class AuslagerungsThread extends Thread {

    private final Storage storage;

    AuslagerungsThread(Storage storage, String name) {

        if (name != null && !name.isEmpty())
            this.setName(name);

        if (storage == null)
            throw new IllegalArgumentException("Storage is null");

        this.storage = storage;

    }


    @Override
    public void run() {

        while (!isInterrupted()) {

            try {
                remove();
                sleep(500);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " storage is full - interrupt");
                this.interrupt();
            }


        }

        System.out.println(Thread.currentThread().getName() + " interrupted");


    }

    private void remove() throws InterruptedException {

        synchronized (storage) {

            while (!storage.isToxicLevelOver50Percent() && !storage.isStorageFull()) {
                System.out.println(Thread.currentThread().getName() + " awaiting full storage to remove a cargo");
                storage.wait();
                System.out.println(Thread.currentThread().getName() + " notify signal received");
            }


            if (storage.isStorageFull()) {
                storage.notifyAll();
                throw new InterruptedException();
            }

            if (storage.removeSmallestToxicCargo()) {
                System.out.println(Thread.currentThread().getName() + " removed toxic cargo");
            } else {
                System.out.println(Thread.currentThread().getName() + " couldnt remove toxic cargo");
            }


            storage.notifyAll();

        }
    }

}
