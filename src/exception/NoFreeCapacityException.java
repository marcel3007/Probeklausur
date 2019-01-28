package exception;

import java.util.Locale;

/**
 * @author Marcel Waldau
 */
public class NoFreeCapacityException extends Exception {

    public NoFreeCapacityException(int capacity, int size) {
        super(String.format(Locale.getDefault(), "No free capacity, Storage capacity: %d | Cargo size: %d", capacity, size));
    }

    public NoFreeCapacityException() {
        super("No free capacity");
    }
}
