package model;

import exception.NoFreeCapacityException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    /**
     * Constructor
     */

    @Test
    void constructor_capacityMinInt() {
        assertThrows(IllegalArgumentException.class, () -> new Storage(Integer.MIN_VALUE));
    }

    @Test
    void constructor_capacityMaxInt() {
        assertThrows(IllegalArgumentException.class, () -> new Storage(Integer.MAX_VALUE));
    }

    @Test
    void constructor_capacityToLarge() {
        assertThrows(IllegalArgumentException.class, () -> new Storage(21));
    }

    @Test
    void constructor_capacityToSmall() {
        assertThrows(IllegalArgumentException.class, () -> new Storage(-1));
    }

    @Test
    void constructor_capacityMin() {
        assertDoesNotThrow(() -> new Storage(0));
    }

    @Test
    void constructor_capacityMax() {
        assertDoesNotThrow(() -> new Storage(20));
    }

    /**
     * addCargo
     */

    @Test
    void addCargo_cargoNull() {
        Storage storage = new Storage(4);
        assertThrows(IllegalArgumentException.class, () -> storage.addCargo(null));
    }

    @Test
    void addCargo_cargoToLarge() {
        Storage storage = new Storage(4);
        assertThrows(IllegalArgumentException.class, () -> storage.addCargo(new CargoImpl(4, false)));
    }

    @Test
    void addCargo_cargoToSmall() {
        Storage storage = new Storage(4);
        assertThrows(IllegalArgumentException.class, () -> storage.addCargo(new CargoImpl(0, false)));
    }

    @Test
    void addCargo_oneCargo() {
        Storage storage = new Storage(4);
        assertDoesNotThrow(() -> storage.addCargo(new CargoImpl(1, false)));
    }

    @Test
    void addCargo_fullCargo() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        assertDoesNotThrow(() -> storage.addCargo(new CargoImpl(1, false)));
    }

    @Test
    void addCargo_storageFull() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        assertThrows(NoFreeCapacityException.class, () -> storage.addCargo(new CargoImpl(1, false)));
    }

    /**
     * isToxicLevelOver50Percent
     */

    @Test
    void isToxicLevelOver50Percent_0_items() {
        Storage storage = new Storage(4);
        assertFalse(storage.isToxicLevelOver50Percent());
    }

    @Test
    void isToxicLevelOver50Percent_halfFull_noToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        assertFalse(storage.isToxicLevelOver50Percent());
    }

    @Test
    void isToxicLevelOver50Percent_full_noToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        assertFalse(storage.isToxicLevelOver50Percent());
    }

    @Test
    void isToxicLevelOver50Percent_halfFull_OneToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, false));
        assertFalse(storage.isToxicLevelOver50Percent());
    }

    @Test
    void isToxicLevelOver50Percent_halfFullWithToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertTrue(storage.isToxicLevelOver50Percent());
    }

    @Test
    void isToxicLevelOver50Percent_FullWithToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertTrue(storage.isToxicLevelOver50Percent());
    }


    /**
     * isToxicLevelUnder50Percent
     */

    @Test
    void isToxicLevelUnder50Percent_0_items() {
        Storage storage = new Storage(4);
        assertTrue(storage.isToxicLevelUnder50Percent());
    }

    @Test
    void isToxicLevelUnder50Percent_halfFull_noToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        assertTrue(storage.isToxicLevelUnder50Percent());
    }

    @Test
    void isToxicLevelUnder50Percent_full_noToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        storage.addCargo(new CargoImpl(1, false));
        assertTrue(storage.isToxicLevelUnder50Percent());
    }

    @Test
    void isToxicLevelUnder50Percent_halfFull_OneToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, false));
        assertTrue(storage.isToxicLevelUnder50Percent());
    }

    @Test
    void isToxicLevelUnder50Percent_halfFullWithToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertFalse(storage.isToxicLevelUnder50Percent());
    }

    @Test
    void isToxicLevelUnder50Percent_FullWithToxicCargos() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertFalse(storage.isToxicLevelUnder50Percent());
    }


    /**
     * isStorageFull
     */

    @Test
    void isStorageFull_empty() {
        Storage storage = new Storage(4);
        assertFalse(storage.isStorageFull());
    }

    @Test
    void isStorageFull_50Percent() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertFalse(storage.isStorageFull());
    }

    @Test
    void isStorageFull_75Percent() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertFalse(storage.isStorageFull());
    }
    @Test
    void isStorageFull_full() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertTrue(storage.isStorageFull());
    }

    /**
     * removeSmallestToxicCargo
     */

    @Test
    void removeSmallestToxicCargo_emptyStorage() {
        Storage storage = new Storage(4);
        assertFalse(storage.removeSmallestToxicCargo());
    }

    @Test
    void removeSmallestToxicCargo_noToxicCargo() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, false));
        assertFalse(storage.removeSmallestToxicCargo());
    }

    @Test
    void removeSmallestToxicCargo_twoToxicCargoDifferentSize() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(2, true));
        assertTrue(storage.removeSmallestToxicCargo());
    }

    @Test
    void removeSmallestToxicCargo_twoToxicCargoEqualSize() throws NoFreeCapacityException {
        Storage storage = new Storage(4);
        storage.addCargo(new CargoImpl(1, true));
        storage.addCargo(new CargoImpl(1, true));
        assertTrue(storage.removeSmallestToxicCargo());
    }
}