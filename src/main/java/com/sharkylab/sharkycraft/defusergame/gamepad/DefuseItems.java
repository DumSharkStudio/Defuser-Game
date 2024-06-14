package com.sharkylab.sharkycraft.defusergame.gamepad;

import com.sharkylab.sharkycraft.defusergame.manager.ConfigManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class DefuseItems {

    private static final NamespacedKey wireKey = new NamespacedKey("defusegame", "imthegreenwire");

    public static ItemStack[] getConfusions() {
        List<String> mList = ConfigManager.getInstance().getConfig("defuseitems.yml").getStringList("confusions");
        ItemStack[] itemStacks = new ItemStack[mList.size()];
        for (int i=0;i<itemStacks.length;i++) {
            Material material = Material.getMaterial(mList.get(i));
            if (material == null) continue;;
            ItemStack itemStack = new ItemStack(material);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(MiniMessage.miniMessage().deserialize(" "));
            itemStack.setItemMeta(itemMeta);
            itemStacks[i] = itemStack;
        }
        return itemStacks;
    }

    public static ItemStack getWireItem() {
        String boom = ConfigManager.getInstance().getConfig("defuseitems.yml").getString("wires");
        if (boom == null) return null;
        Material material = Material.getMaterial(boom);
        if (material == null) return null;
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(MiniMessage.miniMessage().deserialize(" "));
        itemMeta.getPersistentDataContainer().set(wireKey, PersistentDataType.STRING, "Wait... Is this one correct?");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

}
