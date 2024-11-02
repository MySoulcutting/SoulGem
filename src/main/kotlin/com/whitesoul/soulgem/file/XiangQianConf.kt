package com.whitesoul.soulgem.file

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object XiangQianConf {
    @Config("ui/XiangQian.yml")
    lateinit var config: ConfigFile
}
