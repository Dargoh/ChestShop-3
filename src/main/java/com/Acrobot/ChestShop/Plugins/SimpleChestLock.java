package com.Acrobot.ChestShop.Plugins;

import com.Acrobot.ChestShop.Events.Protection.ProtectionCheckEvent;
import com.webkonsept.bukkit.simplechestlock.SCL;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

/**
 * @author Acrobot
 */
public class SimpleChestLock implements Listener {
    private SCL scl;

    public SimpleChestLock(SCL scl) {
        this.scl = scl;
    }

    public static SimpleChestLock getSimpleChestLock(Plugin plugin) {
        if (!(plugin instanceof SCL)) {
            return null;
        }

        return new SimpleChestLock((SCL) plugin);
    }

    @EventHandler
    public void onProtectionCheck(ProtectionCheckEvent event) {
        if (event.getResult() == Event.Result.DENY) {
            return;
        }

        Block block = event.getBlock();
        Player player = event.getPlayer();

        if (!scl.chests.isLocked(block)) {
            return;
        }

        String playerName = player.getName();

        if (!scl.chests.getOwner(block).equals(playerName)) {
            event.setResult(Event.Result.DENY);
        }
    }
}
