package me.Zeron.CensorPlugin

import me.Zeron.CensorPlugin.Resource.prefix
import me.Zeron.CensorPlugin.WordLogger.create
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.FileWriter

class MainCore : JavaPlugin() {
    companion object {
        lateinit var plugin: Plugin
    }
    override fun onEnable() {
        plugin = this
        logger.info("$prefix 플러그인이 활성화 중입니다")
        server.pluginManager.registerEvents(ChatListener, this)
        val descriptionFile = File(dataFolder, "description.txt")
        if (!descriptionFile.exists()) {
            val fileWriter = FileWriter(descriptionFile)
            fileWriter.use {
                it.write("""
                [ 검열 플러그인 ]
                버전 : 1.17
                이 플러그인은 챗 관리 때문에 고생하시는 관리자 분들을 위해서 만들어 봤습니다!
                버전 업그레이드, 다운그레이드가 필요하신 분은 Zeron#6595로 DM주세요. (무료일까요? 아닐까요?)
                무단 배포, 수정 금지!
                혹시라도 저 말고 (Zeron 이라는 닉네임으로 활동 중입니다) 플러그인을 다른 곳에서 배포하거나, 판매하시는 분이 있다면 법적 조치를 취하겠습니다.
                
                
            """.trimIndent())
            }
            create()
        }
    }

    override fun onDisable() {
        logger.info("$prefix 비플러그인이 활성화 중입니다")
    }
}