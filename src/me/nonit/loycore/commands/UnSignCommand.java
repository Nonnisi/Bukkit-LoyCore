package me.nonit.loycore.commands;

import me.nonit.loycore.LoyCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class UnSignCommand implements CommandExecutor
{
    public UnSignCommand()
    {
    }

    @Override
    public boolean onCommand( CommandSender sender, Command cmd, String label, String[] args )
    {
        if( sender instanceof Player )
        {
            Player player = (Player) sender;
            ItemStack is = player.getItemInHand();

            if( player.hasPermission( "loy.unsign" ) )
            {
                if( is.getType() == Material.WRITTEN_BOOK )
                {
                    if( is.hasItemMeta() )
                    {
                        ItemMeta meta = is.getItemMeta();
                        if( ( meta instanceof BookMeta ) )
                        {
                            BookMeta bookMeta = ( BookMeta ) meta;
                            if( ( bookMeta.hasAuthor() ) && ( bookMeta.getAuthor().equalsIgnoreCase( player.getName() ) || player.hasPermission( "loy.unsign.other" ) ) )
                            {
                                BookMeta newMeta = bookMeta.clone();
                                ItemStack newIs = new ItemStack( Material.BOOK_AND_QUILL );
                                newIs.setItemMeta( newMeta );
                                player.setItemInHand( newIs );

                                player.sendMessage( LoyCore.getPfx() + ChatColor.GREEN + "Unsigned \"" + ChatColor.WHITE + bookMeta.getTitle() + ChatColor.GREEN + "\"!" );
                            }
                            else
                            {
                                player.sendMessage( LoyCore.getPfx() + ChatColor.RED + "You cannot unsign other peoples books!" );
                            }
                        }
                    }
                }
            }
            else
            {
                player.sendMessage( LoyCore.getPfx() + ChatColor.RED + "No permission :(" );
            }
        }

        return true;
    }
}
