package com.whitesoul.soulgem.file

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object ChaiXieConf {
    @Config("ui/ChaiXie.yml")
    lateinit var config: ConfigFile
}