package tsp.azuma.api;

public interface ManaStorage {

    /**
     * Returns the current amount of mana stored by the mana holder.
     *
     * @return  amount of mana stored by mana holder
     */
    int getMana();

    /**
     * Returns the maximum amount of mana this mana holder can store.
     *
     * @return  maximum amount of mana
     */
    int getMaxMana();

    int getMaxManaInput();

    int getMaxManaOutput();

    /**
     * Inserts the given amount of mana into the mana holder. Returns any mana that could not be inserted.
     *
     * <p>
     * It is the responsibility of the receiver to verify the amount of mana being inserted.
     *
     * @param amount  amount of mana to insert into this mana holder.
     * @return  remaining amount of mana that was not inserted.
     */
    int insertMana(int amount);
    int extract(int amount);

    boolean canInsert();
    boolean canExtract();
}
