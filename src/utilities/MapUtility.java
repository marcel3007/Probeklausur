package utilities;

import java.util.TreeMap;

/**
 * Utility class for Maps
 *
 * @author Marcel Waldau
 */
public class MapUtility {

    /**
     * returns a new ID from a TreeMap with Long as Key
     *
     * @param map      the map
     * @param startKey the first available ID
     * @return a new key
     */
    public static long getNewKey(TreeMap<Long, ?> map, long startKey) throws IllegalArgumentException, IndexOutOfBoundsException {

        if (map == null)
            throw new IllegalArgumentException("map is null");

        if (startKey == Long.MAX_VALUE)
            throw new IllegalArgumentException("startKey to large");

        if (map.isEmpty())
            return startKey;


        for (long i = startKey; i <= map.lastKey(); i++) {
            if (map.get(i) == null)
                return i;
        }

        if (map.lastKey() == Long.MAX_VALUE)
            throw new IndexOutOfBoundsException();

        return map.lastKey() + 1;
    }


}
