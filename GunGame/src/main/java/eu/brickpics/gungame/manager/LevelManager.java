package eu.brickpics.gungame.manager;

import eu.brickpics.gungame.manager.multilang.AchievementManager;
import eu.brickpics.gungame.manager.util.vendor.ItemManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class LevelManager {
    public static void setCurrentLevel(Player p, int xp) {
        p.setLevel(xp);
    }

    public static void refreshXPBar(Player p) {
        p.setExp(0.01666F * getCurrentLevel(p));
    }

    public static int getCurrentLevel(Player p) {
        return p.getLevel();
    }

    public static void levelUpByOne(Player p) {
        p.setExp(p.getExp() + 0.01666F);
        p.setLevel(p.getLevel() + 1);
        giveEquip(p);
    }

    public static void giveEquip(Player p) {
        int level = getCurrentLevel(p);
        PlayerInventory inv = p.getInventory();
        refreshXPBar(p);
        inv.clear();
        inv.setArmorContents(null);
        switch (level) {
            case 0:
                inv.setItem(0, (new ItemManager(Material.WOOD_AXE, 1)).setUnbreakable(true).build());
                return;
            case 1:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).setUnbreakable(true).build());
                return;
            case 2:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).setUnbreakable(true).build());
                return;
            case 3:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).setUnbreakable(true).build());
                return;
            case 4:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).setUnbreakable(true).build());
                return;
            case 5:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).setUnbreakable(true).build());

                if (!AchievementManager.hasAchievement(5, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(5, true, p.getUniqueId().toString());
                return;
            case 6:
                inv.setItem(0, (new ItemManager(Material.WOOD_AXE, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 7:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 8:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 9:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 10:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 11:
                inv.setItem(0, (new ItemManager(Material.WOOD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(6, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(6, true, p.getUniqueId().toString());
                return;
            case 12:
                inv.setItem(0, (new ItemManager(Material.GOLD_AXE, 1)).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 13:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).build());
                inv.setChestplate((new ItemManager(Material.LEATHER_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 14:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.LEATHER_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 15:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.LEATHER_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 16:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.LEATHER_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 17:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(7, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(7, true, p.getUniqueId().toString());
                return;
            case 18:
                inv.setItem(0, (new ItemManager(Material.GOLD_AXE, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 19:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 20:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 21:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 22:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 23:
                inv.setItem(0, (new ItemManager(Material.GOLD_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(8, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(8, true, p.getUniqueId().toString());
                return;
            case 24:
                inv.setItem(0, (new ItemManager(Material.STONE_AXE, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 25:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.GOLD_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 26:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.GOLD_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 27:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.GOLD_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 28:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.GOLD_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 29:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(9, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(9, true, p.getUniqueId().toString());
                return;
            case 30:
                inv.setItem(0, (new ItemManager(Material.STONE_AXE, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 31:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 32:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 33:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 34:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 35:
                inv.setItem(0, (new ItemManager(Material.STONE_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(10, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(10, true, p.getUniqueId().toString());
                return;
            case 36:
                inv.setItem(0, (new ItemManager(Material.IRON_AXE, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 37:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.CHAINMAIL_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 38:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.CHAINMAIL_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 39:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.CHAINMAIL_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 40:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.CHAINMAIL_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 41:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(11, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(11, true, p.getUniqueId().toString());
                return;
            case 42:
                inv.setItem(0, (new ItemManager(Material.IRON_AXE, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 43:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 44:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 45:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 46:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 47:
                inv.setItem(0, (new ItemManager(Material.IRON_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(12, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(12, true, p.getUniqueId().toString());
                return;
            case 48:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_AXE, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 49:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.IRON_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 50:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.IRON_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 51:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.IRON_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 52:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.IRON_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                return;
            case 53:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(13, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(13, true, p.getUniqueId().toString());
                return;
            case 54:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_AXE, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 55:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 56:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 57:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 58:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).setUnbreakable(true).build());
                return;
            case 59:
                inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
                inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
                if (!AchievementManager.hasAchievement(14, p.getUniqueId().toString()))
                    AchievementManager.setAchievement(14, true, p.getUniqueId().toString());
                return;
        }
        if (level > 59) {
            inv.setItem(0, (new ItemManager(Material.DIAMOND_SWORD, 1)).addEnchantment(Enchantment.DAMAGE_ALL, 1).setUnbreakable(true).build());
            inv.setChestplate((new ItemManager(Material.DIAMOND_CHESTPLATE, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
            inv.setBoots((new ItemManager(Material.DIAMOND_BOOTS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
            inv.setHelmet((new ItemManager(Material.DIAMOND_HELMET, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
            inv.setLeggings((new ItemManager(Material.DIAMOND_LEGGINGS, 1)).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).setUnbreakable(true).build());
            inv.setItem(1, (new ItemManager(Material.BOW, 1)).setDisplayName("§r§6The Punisher").setUnbreakable(true).addEnchantment(Enchantment.ARROW_INFINITE, 1).build());
            inv.setItem(8, new ItemStack(Material.ARROW, 1));
        } else {
            inv.setItem(0, (new ItemManager(Material.WOOD_AXE, 1)).setUnbreakable(true).build());
        }
    }
}
