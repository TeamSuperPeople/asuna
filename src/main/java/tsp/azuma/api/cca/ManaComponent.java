package tsp.azuma.api.cca;

import nerdhub.cardinal.components.api.component.Component;

public interface ManaComponent extends Component {
    int getMana();
    int getMaxMana();
    void decrement();
    void decrement(int amount);
    void increment();
    void increment(int amount);
    void setMana(int mana);
}
