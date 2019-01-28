package model;

import exception.NoFreeCapacityException;

import java.util.Map;
import java.util.TreeMap;

import static utilities.MapUtility.getNewKey;

public class Storage {
    /**
     * first available POSITION ID
     */
    private static final long FIRST_POSITION_ID = 1;

    private static int MIN_CARGO_SIZE = 1;
    private static int MAX_CARGO_SIZE = 3;

    /**
     * the maximum capacity of the main.java.storage
     */
    private int capacity;

    /**
     * a map of all StorageItems, key is the position, value the StorageItem
     */
    private TreeMap<Long, Cargo> itemsMap;

    /**
     * construct a new Storage
     *
     * @param capacity the capacity of the Storage
     */
    public Storage(int capacity) {
        if(capacity < 0)
            throw new IllegalArgumentException();
        if(capacity > 20)
            throw new IllegalArgumentException();

        this.capacity = capacity;
        this.itemsMap = new TreeMap<>();
    }


    /**
     * add a new cargo item to the main.java.storage
     *
     * @param cargo Cargo Item to be added
     * @throws NoFreeCapacityException  if not enough space is available in main.java.storage
     * @throws IllegalArgumentException if cargo is null
     */
    public void addCargo(Cargo cargo) throws NoFreeCapacityException, IllegalArgumentException {

        if (cargo == null)
            throw new IllegalArgumentException("cargo is null");

        if (cargo.getSize() > MAX_CARGO_SIZE || cargo.getSize() < MIN_CARGO_SIZE)
            throw new IllegalArgumentException("invalid cargo size");

        int freeCapacity = getFreeCapacity();

        if (freeCapacity < cargo.getSize())
            throw new NoFreeCapacityException(freeCapacity, cargo.getSize());

        long position = getNewKey(itemsMap, FIRST_POSITION_ID);

        itemsMap.put(position, cargo);

    }

    public synchronized boolean removeSmallestToxicCargo() {

        if (itemsMap.isEmpty())
            return false;

        Long smallestPosition = itemsMap.firstKey();


        for (Map.Entry<Long, Cargo> entry : itemsMap.entrySet()) {

            Cargo cargo = entry.getValue();

            if(!cargo.isToxic())
                continue;

            if (cargo.getSize() < itemsMap.get(smallestPosition).getSize())
                smallestPosition = entry.getKey();
        }

        Cargo cargo = null;

        if (itemsMap.get(smallestPosition).isToxic())
            cargo = itemsMap.remove(smallestPosition);

        return cargo != null;
    }


    public synchronized boolean isToxicLevelOver50Percent() {
        return getToxicLevel() >= ((double) capacity) / 2;
    }

    public synchronized boolean isToxicLevelUnder50Percent() {
        return getToxicLevel() < ((double) capacity) / 2;
    }

    public synchronized boolean isStorageFull(){
        return getFreeCapacity() == 0;
    }

    private int getToxicLevel(){
        int toxicLevel = 0;

        for (Map.Entry<Long, Cargo> entry : itemsMap.entrySet()) {

            if (entry.getValue().isToxic())
                toxicLevel += entry.getValue().getSize();

        }

        return toxicLevel;
    }


    @Override
    public String toString() {
        return "Storage{" +
                "capacity=" + capacity +
                ", itemsMap=" + itemsMap +
                '}';
    }

    private int getFreeCapacity() {
        int totalUsed = itemsMap
                .values()
                .stream()
                .map(Cargo::getSize)
                .mapToInt(Integer::intValue)
                .sum();

        return capacity - totalUsed;
    }
}
