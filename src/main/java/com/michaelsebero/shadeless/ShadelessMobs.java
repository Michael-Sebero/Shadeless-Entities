package com.michaelsebero.shadeless;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ShadelessMobs.MODID, name = ShadelessMobs.NAME, version = ShadelessMobs.VERSION, clientSideOnly = true)
public class ShadelessMobs {
    
    public static final String MODID = "shadelessmobs";
    public static final String NAME = "Shadeless Mobs";
    public static final String VERSION = "1.0";
    
    private RenderManager renderManager;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        System.out.println("[ShadelessMobs] Initialized - entities will render without shading");
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    @SideOnly(Side.CLIENT)
    public void onRenderLivingPre(RenderLivingEvent.Pre<EntityLivingBase> event) {
        // Simple null check - event.getEntity() is never null in practice
        if (event.getEntity() != null) {
            GlStateManager.disableLighting();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    @SideOnly(Side.CLIENT)
    public void onRenderLivingPost(RenderLivingEvent.Post<EntityLivingBase> event) {
        // Simple null check - event.getEntity() is never null in practice
        if (event.getEntity() != null) {
            GlStateManager.enableLighting();
        }
    }
}
