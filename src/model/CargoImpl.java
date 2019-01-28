package model;

public class CargoImpl implements Cargo {

    private int size;
    private boolean isToxic;

    public CargoImpl(int size, boolean isToxic) {

        if(size > MAX_SIZE || size < MIN_SIZE)
            throw new IllegalArgumentException("cargo size invalid");

        this.size = size;
        this.isToxic = isToxic;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isToxic() {
        return isToxic;
    }

    @Override
    public String toString() {
        return "CargoImpl{" +
                "size=" + size +
                ", isToxic=" + isToxic +
                '}';
    }
}
