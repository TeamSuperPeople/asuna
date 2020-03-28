package tsp.asuna.api;

import nerdhub.cardinal.components.api.component.Component;

public interface ManaComponent extends Component {
    int getMana();
    int getMaxMana();
    void decrement();
    void decrement(int amount);
    void increment();
}
