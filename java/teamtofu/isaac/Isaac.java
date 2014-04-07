package teamtofu.isaac;



import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import teamtofu.isaac.common.CommonProxy;
import teamtofu.isaac.item.ItemCollectable;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


@Mod(modid = "Isaac", name = "The Binding of Isaac", version = "1.0.0")

public class Isaac {
	
    @Instance("Isaac")
    public static Isaac instance;
    
    public static FMLEventChannel channel;
    
    public static CreativeTabs tab;
    
    public static Item wafer;
	
	@SidedProxy(clientSide = "teamtofu.isaac.client.ClientProxy", serverSide = "teamtofu.isaac.common.CommonProxy")
	public static CommonProxy proxy;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		wafer = new ItemCollectable().setTextureName("isaac:wafer").setUnlocalizedName("isaac:wafer");
		GameRegistry.registerItem(wafer, "wafer");
		tab = new IsaacTab(CreativeTabs.getNextID(), "isaac");
	}
	
	

	@EventHandler
	public void load(FMLInitializationEvent event) {

		MinecraftForge.EVENT_BUS.register(new IsaacEventHandler());
		proxy.registerRenderers();
	}
	
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	    
	}


	private class IsaacTab extends CreativeTabs {
		public IsaacTab(int par1, String par2Str) {
			super(par1, par2Str);
		}
		
      
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
		
			return Item.getItemFromBlock(Blocks.dirt);
		}	
	}
}