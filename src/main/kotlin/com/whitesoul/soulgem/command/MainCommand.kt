package com.whitesoul.soulgem.command

import com.whitesoul.soulgem.command.sub.GiveGemCommand
import com.whitesoul.soulgem.command.sub.GiveLuckyGemCommand
import com.whitesoul.soulgem.command.sub.OpenCommand
import com.whitesoul.soulgem.command.sub.ReloadCommand
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.expansion.createHelper

@CommandHeader("soulgem", ["sg","sgem"], "魂之宝石")
object MainCommand {
    @CommandBody(permission = "soulgem.help")
    val main = mainCommand {
        createHelper()
    }

    @CommandBody(permission = "soulgem.give")
    val giveGem = GiveGemCommand.command
    @CommandBody(permission = "soulgem.give")
    val giveLuckyGem = GiveLuckyGemCommand.command

    @CommandBody(permission = "soulgem.open")
    val open = OpenCommand.command

    @CommandBody(permission = "soulgem.reload")
    val reload = ReloadCommand.command
}