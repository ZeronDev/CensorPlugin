package me.Zeron.CensorPlugin

import me.Zeron.CensorPlugin.Resource.badWords
import me.Zeron.CensorPlugin.WordLogger.add
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

object ChatListener : Listener {
    @EventHandler
    fun onChat(e: AsyncPlayerChatEvent) {
        val originalMessage = e.message
        var ifCensored = false
        badWords.forEach {
            if (e.message.contains(it)) {
                ifCensored = true
                e.message = e.message.replace(it, "*".repeat(it.length))
            }
        }
        if (ifCensored) add(e.player.name, e.player.uniqueId, originalMessage)
    }
}