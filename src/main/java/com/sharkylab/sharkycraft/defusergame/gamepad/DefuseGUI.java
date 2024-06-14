package com.sharkylab.sharkycraft.defusergame.gamepad;

import com.sharkylab.sharkycraft.defusergame.Defuser_Game;
import com.sharkylab.sharkycraft.defusergame.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;
import java.util.logging.Level;

public class DefuseGUI {

    private final Plugin plugin = Defuser_Game.getPlugin(Defuser_Game.class);
    private final FileConfiguration config = ConfigManager.getInstance().getConfig("config.yml");
    private Inventory gui;

    public DefuseGUI() {
        gui = Bukkit.createInventory(null, 54);
    }

    public Inventory getGamePad() {
        return gui;
    }

    public void randomizeItems() {
        final ItemStack[] items = DefuseItems.getConfusions();

        if (DefuseItems.getWireItem() == null || items.length == 0) {
            plugin.getLogger().log(Level.SEVERE, "Items for defuse gui not found.");
            return;
        }

        gui.clear();

        Set<Integer> chosenSlots = new HashSet<>();
        int wire_amount = (int) Math.ceil(gui.getSize() * config.getDouble("game.wires"));
        for (int i = 0; i < wire_amount; i++) {
            gui.setItem(uniqueRandomSlot(gui.getSize(), chosenSlots), DefuseItems.getWireItem());
        }

        List<ItemStack> itemList = new ArrayList<>();
        Collections.addAll(itemList, items);
        Collections.shuffle(itemList);

        for (int i = 0; i < gui.getSize(); i++) {
            if (gui.getItem(i) != null) continue;
            gui.setItem(i, itemList.get(new Random().nextInt(items.length)));
        }
    }

    private Integer uniqueRandomSlot(int i, Set<Integer> chosenSlots) {
        int slot = new Random().nextInt(i);
        while (chosenSlots.contains(slot)) {
            slot = new Random().nextInt(i);
        }
        chosenSlots.add(slot);
        return slot;
    }

}
