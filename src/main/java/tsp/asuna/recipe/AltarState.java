package tsp.asuna.recipe;

import tsp.asuna.entity.InfusionAltarCoreBlockEntity;
import tsp.asuna.entity.InfusionAltarPedestalBlockEntity;

import java.util.List;

public class AltarState {

    private final InfusionAltarCoreBlockEntity core;
    private final List<InfusionAltarPedestalBlockEntity> firstRingAltars;
    private final List<InfusionAltarPedestalBlockEntity> secondRingAltars;
    private final List<InfusionAltarPedestalBlockEntity> thirdRingAltars;

    public AltarState(InfusionAltarCoreBlockEntity core, List<InfusionAltarPedestalBlockEntity> firstRingAltars, List<InfusionAltarPedestalBlockEntity> secondRingAltars, List<InfusionAltarPedestalBlockEntity> thirdRingAltars) {
        this.core = core;
        this.firstRingAltars = firstRingAltars;
        this.secondRingAltars = secondRingAltars;
        this.thirdRingAltars = thirdRingAltars;
    }

    public InfusionAltarCoreBlockEntity getCore() {
        return core;
    }

    public List<InfusionAltarPedestalBlockEntity> getFirstRingPedestals() {
        return firstRingAltars;
    }

    public List<InfusionAltarPedestalBlockEntity> getSecondRingAltars() {
        return secondRingAltars;
    }

    public List<InfusionAltarPedestalBlockEntity> getThirdRingAltars() {
        return thirdRingAltars;
    }
}
