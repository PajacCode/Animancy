package com.omicron.animancy.common.item;

import com.omicron.animancy.Test2;
import com.omicron.animancy.client.BookScreen;
import com.omicron.animancy.common.network.APINetwork;
import com.omicron.animancy.common.network.TestPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;

public class BookItem extends Item {
    public BookItem() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand)
    {
        for(RegistryObject a : Test2.SPELLS.getEntries())
        {
            System.out.println(a.get().getRegistryName());
        }
        if(world.isClientSide())
        {
            Minecraft.getInstance().setScreen(new BookScreen());
        }
        System.out.println("test");
        ItemStack itemStack = playerEntity.getItemInHand(hand);
        Entity entity = Minecraft.getInstance().crosshairPickEntity;
        if(entity != null)
        {
            //APINetwork.sendToServer(new TestPacket(entity.getId()));
            System.out.println(entity);
        }
        return ActionResult.success(itemStack);
    }
}
