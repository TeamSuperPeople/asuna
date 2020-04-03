package tsp.asuna.recipe;

import tsp.asuna.block.InfusionAltarPedestalBlock;
import tsp.asuna.entities.InfusionAltarCoreBlockEntity;
import tsp.asuna.entities.InfusionAltarPedestalBlockEntity;

import java.util.List;

public class AltarState {

    private InfusionAltarCoreBlockEntity core;
    private List<InfusionAltarPedestalBlockEntity> firstRingAltars;
    private List<InfusionAltarPedestalBlockEntity> secondRingAltars;
    private List<InfusionAltarPedestalBlockEntity> thirdRingAltars;

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
