package tsp.asuna.api.cca;

import nerdhub.cardinal.components.api.component.Component;
import net.minecraft.nbt.CompoundTag;

public class JoinComponent implements Component {

    private boolean hasJoined = false;

    public boolean hasJoined() {
        return hasJoined;
    }

    public void setHasJoined(boolean hasJoined) {
        this.hasJoined = hasJoined;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        this.hasJoined = tag.getBoolean("HasJoined");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putBoolean("HasJoined", hasJoined);
        return tag;
    }
}
