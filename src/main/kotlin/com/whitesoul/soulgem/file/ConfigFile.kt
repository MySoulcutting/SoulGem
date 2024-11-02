package com.whitesoul.soulgem.file

import taboolib.module.configuration.Config
import taboolib.module.configuration.ConfigFile

object ConfigFile {
    @Config("config.yml")
    lateinit var config: ConfigFile
}