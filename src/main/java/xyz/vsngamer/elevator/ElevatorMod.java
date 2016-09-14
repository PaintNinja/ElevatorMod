package xyz.vsngamer.elevator;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.vsngamer.elevator.init.ModBlocks;
import xyz.vsngamer.elevator.init.ModConfig;
import xyz.vsngamer.elevator.init.ModCrafting;
import xyz.vsngamer.elevator.init.ModSounds;
import xyz.vsngamer.elevator.proxy.CommonProxy;
import xyz.vsngamer.elevator.tileentity.TileEntityElevator;

@Mod(modid = Ref.MOD_ID, name = Ref.NAME, version = Ref.VERSION, acceptedMinecraftVersions = Ref.ACCPEPTED_VERSIONS, guiFactory = Ref.GUI_FACTORY)
public class ElevatorMod {

	@Instance
	public static ElevatorMod instance;

	@SidedProxy(clientSide = Ref.CLIENT_PROXY_CLASS, serverSide = Ref.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final CreativeTabs CREATIVE_TAB = new ElevatorModTab();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {

		ModBlocks.init();
		ModBlocks.register();
		proxy.preInit();
		ModSounds.registerSounds();
		
		File configDir = new File(event.getModConfigurationDirectory(), "ElevatorMod");
		ModConfig.init(new File(configDir, "ElevatorMod.cfg"));
		
		MinecraftForge.EVENT_BUS.register(new ModConfig());
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModCrafting.register();
		GameRegistry.registerTileEntity(TileEntityElevator.class, Ref.MOD_ID + "TileEntityElevator");
		

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
