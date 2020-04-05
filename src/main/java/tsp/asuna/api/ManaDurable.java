package tsp.asuna.api;

public interface ManaDurable {
    int getMaxMana();

    default int getStartingMana() {
        return getMaxMana();
    }
}
