package com.dbydd.micro_machinery.gui.klin;

import com.dbydd.micro_machinery.blocks.tileentities.TileEntityKlin;
import com.dbydd.micro_machinery.gui.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerKlin extends ContainerBase {
    private TileEntityKlin tileentity;
    private int melttime, currentmelttimeTime, burntime, maxburntime;
    private FluidTank tank;

    public ContainerKlin(EntityPlayer player, TileEntityKlin tileEntity) {
        super();

        this.tileentity = tileEntity;
        this.player = player;
        IItemHandler itemHandler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
//        Add the fuxking slot.
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 0, 40, 24));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 1, 40, 50));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 2, 80, 50));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 3, 120, 50));
        this.addSlotToContainer(new SlotItemHandler(itemHandler, 4, 120, 24));

        this.drawInventory(8, 84);

        this.tank = tileentity.fluidhandler;
    }


    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (IContainerListener iContainerListener : this.listeners) {

            if (this.melttime != this.tileentity.getField(0))
                iContainerListener.sendWindowProperty(this, 0, this.tileentity.getField(0));
            if (this.burntime != this.tileentity.getField(2))
                iContainerListener.sendWindowProperty(this, 1, this.tileentity.getField(2));
            if (this.maxburntime != this.tileentity.getField(3))
                iContainerListener.sendWindowProperty(this, 1, this.tileentity.getField(3));
            if (this.currentmelttimeTime != this.tileentity.getField(1))
                iContainerListener.sendWindowProperty(this, 2, this.tileentity.getField(1));
        }

        this.melttime = this.tileentity.getField(0);
        this.currentmelttimeTime = this.tileentity.getField(1);
        this.burntime = this.tileentity.getField(2);
        this.maxburntime = this.tileentity.getField(3);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn) {
        super.onContainerClosed(playerIn);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileentity.isUsableByPlayer(playerIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int data) {
        this.tileentity.setField(id, data);
    }

}
