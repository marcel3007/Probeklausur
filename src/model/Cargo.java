package model;

public interface Cargo {

    int MIN_SIZE = 1;
    int MAX_SIZE = 5;

    int getSize();
    boolean isToxic();
}
