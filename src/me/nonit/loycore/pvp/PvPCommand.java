package me.nonit.loycore.pvp;

import me.nonit.loycore.LoyCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor
{
    private PvP pvp;
    private PvPListener listener;

    public PvPCommand( PvPListener listener, PvP pvp )
    {
        this.listener = listener;
        this.pvp = pvp;
    }

    @Override
    public boolean onCommand( CommandSender sender, Command command, String s, String[] args )
    {
        if( !sender.hasPermission( "loy.pvp" ) )
        {
            sender.sendMessage( LoyCore.getPfx() + ChatColor.RED + "You don't got permission for that!" );
            return true;
        }

        Player player = (Player) sender;

        if ( pvp.isPvPWorld( player.getWorld().getName() ) )
        {
            player.sendMessage( LoyCore.getPfx() + ChatColor.RED + "PvP cant be disabled here!" );
            return true;
        }

        boolean isEnabled = listener.containsPvPPlayer( player );

        if( isEnabled )
        {
            player.sendMessage( LoyCore.getPfx() + "PvP is now off, you're safe!" );
            listener.removePvPPlayer( player );
            return true;
        }
        else
        {
            player.sendMessage( LoyCore.getPfx() + ChatColor.RED + "PvP is now on, g'luck!" );
            listener.addPvPPlayer( player );
            return true;
        }
    }
}
