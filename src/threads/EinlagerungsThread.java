package threads;

import exception.NoFreeCapacityException;
import model.Cargo;
import model.CargoImpl;
import model.Storage;

import java.util.Random;


public class EinlagerungsThread extends Thread {


    private final Storage storage;

    EinlagerungsThread(Storage storage, String name) {

        if (name != null && !name.isEmpty())
            this.setName(name);

        if (storage == null)
            throw new IllegalArgumentException("Storage is null");

        this.storage = storage;
    }

    @Override
    public void run() {

        try {
            while (!isInterrupted()) {

                try {
                    produce();
                    sleep(200);
                } catch (NoFreeCapacityException e) {
                    System.out.println(Thread.currentThread().getName() + " no free capacity in " + storage.toString());
                    this.interrupt();
                }


            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " interrupted " + e.getMessage());
        }

    }

    private void produce() throws NoFreeCapacityException, InterruptedException {

        synchronized (storage) {


            while (storage.isToxicLevelOver50Percent() && !storage.isStorageFull()) {
                System.out.println(Thread.currentThread().getName() + " storage is half full with toxic cargos - waiting for removing");
                storage.wait();
                System.out.println(Thread.currentThread().getName() + " notify signal received");
            }

            if (storage.isStorageFull()) {
                storage.notifyAll();
                throw new InterruptedException();
            }

//            Cargo cargo = new CargoImpl(1, ThreadLocalRandom.current().nextBoolean());
            Cargo cargo = new CargoImpl(1, true);


            try {
                storage.addCargo(cargo);
                System.out.println(Thread.currentThread().getName() + " added cargo to storage ");
            } catch (NoFreeCapacityException e) {
                storage.notifyAll();
                throw new NoFreeCapacityException();
            }

            if (storage.isToxicLevelOver50Percent())
                storage.notifyAll();

        }
    }
}