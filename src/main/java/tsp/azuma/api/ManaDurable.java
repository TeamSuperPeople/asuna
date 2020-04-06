package tsp.azuma.api;

public interface ManaDurable {
    int getMaxMana();

    default int getStartingMana() {
        return getMaxMana();
    }
}
