package com.stalker.sixnights;

import com.mojang.brigadier.Command;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = StalkerSixNights.MOD_ID)
public final class StalkerCommands {
    private StalkerCommands() {}

    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("stalker")
                .then(Commands.literal("spawn").requires(source -> source.hasPermission(2)).executes(ctx -> {
                    if (!(ctx.getSource().getEntity() instanceof ServerPlayer player)) {
                        ctx.getSource().sendFailure(Component.literal("Run this command as a player."));
                        return 0;
                    }
                    Vec3 look = player.getLookAngle().normalize();
                    StalkerEntity stalker = StalkerRegistry.STALKER.get().create(player.serverLevel());
                    if (stalker == null) return 0;
                    stalker.moveTo(player.getX() + look.x * 3.0, player.getY(), player.getZ() + look.z * 3.0, player.getYRot() + 180.0F, 0.0F);
                    stalker.setCustomName(Component.literal("§0The Stalker"));
                    stalker.setCustomNameVisible(true);
                    if (player.serverLevel().addFreshEntity(stalker)) {
                        ctx.getSource().sendSuccess(() -> Component.literal("The Stalker has been summoned."), false);
                        return Command.SINGLE_SUCCESS;
                    }
                    return 0;
                }))
                .then(Commands.literal("remove").requires(source -> source.hasPermission(2)).executes(ctx -> {
                    if (!(ctx.getSource().getEntity() instanceof ServerPlayer player)) return 0;
                    var nearby = player.serverLevel().getEntitiesOfClass(StalkerEntity.class, player.getBoundingBox().inflate(64.0));
                    nearby.forEach(net.minecraft.world.entity.Entity::discard);
                    ctx.getSource().sendSuccess(() -> Component.literal("Removed " + nearby.size() + " Stalker(s) within 64 blocks."), false);
                    return nearby.size();
                })));
    }
}
