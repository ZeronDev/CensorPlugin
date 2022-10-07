package me.Zeron.CensorPlugin

import io.papermc.paper.event.player.AsyncChatEvent
import me.Zeron.CensorPlugin.Resource.badWords
import me.Zeron.CensorPlugin.WordLogger.add
import net.kyori.adventure.text.Component.text
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object ChatListener : Listener {
    @EventHandler
    fun onChat(e: AsyncChatEvent) {
        var ifCensored = false
        badWords.forEach {
            if (e.message().contains(text(it))) {
                ifCensored = true
                e.message(text(e.message().toString().replace(it, "#".repeat(it.length))))
            }
        }
        if (ifCensored) add(e.player.name, e.player.uniqueId, e.message())
    }
}