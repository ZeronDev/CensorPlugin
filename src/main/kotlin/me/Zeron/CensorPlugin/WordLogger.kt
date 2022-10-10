package me.Zeron.CensorPlugin

import me.Zeron.CensorPlugin.MainCore.Companion.plugin
import net.kyori.adventure.text.Component
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object WordLogger {
    fun create() {
        val logger = File(plugin.dataFolder, "ChatLogger.txt")
        if (!logger.exists()) {
            FileWriter(logger).use { it.write("") }
        }
    }
    fun add(playerName: String, uuid: UUID, message: String) {
        create()
        val logger = File(plugin.dataFolder, "ChatLogger.txt")
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd] HH:mm")
        val formatted = currentTime.format(formatter)
        FileWriter(logger, true).use {
            it.write("[$formatted] <$playerName> ($uuid) $message\n")
        }
    }
    fun signAdd(playerName: String, uuid: UUID, message: String) {
        create()
        val logger = File(plugin.dataFolder, "ChatLogger.txt")
        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd] HH:mm")
        val formatted = currentTime.format(formatter)
        FileWriter(logger, true).use {
            it.write("[$formatted] <$playerName> ($uuid) $message\n - SIGN")
        }
    }
}