package me.Zeron.CensorPlugin

import me.Zeron.CensorPlugin.Resource.badWords
import me.Zeron.CensorPlugin.WordLogger.add
import me.Zeron.CensorPlugin.WordLogger.signAdd
import net.kyori.adventure.text.Component.text
import org.bukkit.block.Sign
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.AsyncPlayerChatEvent

object ChatListener : Listener {
    @EventHandler
    @SuppressWarnings("deprecation")
    fun onChat(e: AsyncPlayerChatEvent) {
        val originalMessage = e.message
        var isCensored = false
        badWords.forEach {
            if (e.message.contains(it)) {
                isCensored = true
                e.message = e.message.replace(it, "*".repeat(it.length))
            }
        }
        if (isCensored) add(e.player.name, e.player.uniqueId, originalMessage)
    }
    @EventHandler
    fun onPlace(e: BlockPlaceEvent) {
        if (e.block is Sign) {
            var isCensored = false
            val sign = e.block as Sign
            val signLine = sign.lines()
            for (lineNum in 0 until signLine.size) {
                badWords.forEach { word ->
                    if (signLine[lineNum].contains(text(word))) {
                        isCensored = true
                        sign.line(lineNum)
                        sign.update()
                    }
                }
            }
            if (isCensored) signAdd(e.player.name, e.player.uniqueId, signLine.joinToString(" "))
        }
    }
}