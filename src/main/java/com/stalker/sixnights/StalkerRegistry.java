package com.stalker.sixnights;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class StalkerRegistry {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, StalkerSixNights.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, StalkerSixNights.MOD_ID);

    public static final RegistryObject<EntityType<StalkerEntity>> STALKER = ENTITY_TYPES.register("stalker", () ->
            EntityType.Builder.of(StalkerEntity::new, MobCategory.MONSTER)
                    .sized(0.68F, 2.85F).clientTrackingRange(12).updateInterval(2)
                    .build("stalkersixnights:stalker"));

    public static final RegistryObject<Item> STALKER_SPAWN_EGG = ITEMS.register("stalker_spawn_egg", () ->
            new SpawnEggItem(STALKER, 0x080808, 0x5A1018, new Item.Properties()));

    private StalkerRegistry() {}
}
