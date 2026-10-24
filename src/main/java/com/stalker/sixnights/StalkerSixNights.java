package com.stalker.sixnights;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(StalkerSixNights.MOD_ID)
public final class StalkerSixNights {
    public static final String MOD_ID = "stalkersixnights";

    public StalkerSixNights() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        StalkerRegistry.ENTITY_TYPES.register(modBus);
        StalkerRegistry.ITEMS.register(modBus);
        modBus.addListener(this::createEntityAttributes);
        modBus.addListener(this::addCreativeItems);
    }

    private void addCreativeItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
            event.accept(StalkerRegistry.STALKER_SPAWN_EGG.get());
        }
    }

    private void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(StalkerRegistry.STALKER.get(), StalkerEntity.createAttributes().build());
    }
}
