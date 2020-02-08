/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

package com.almasb.fxgl.app.services

import com.almasb.fxgl.core.EngineService
import com.almasb.fxgl.core.Inject
import com.almasb.fxgl.localization.Language
import com.almasb.fxgl.localization.LocalizationService
import com.almasb.fxgl.ui.FontType
import com.almasb.fxgl.ui.UIFactoryService
import com.almasb.sslogger.Logger
import javafx.beans.property.ObjectProperty

/**
 *
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
class ConfigureServicesService : EngineService() {

    private val log = Logger.get(javaClass)

    @Inject("language")
    private lateinit var language: ObjectProperty<Language>

    @Inject("supportedLanguages")
    private lateinit var supportedLanguages: List<Language>

    @Inject("fontUI")
    private lateinit var fontUI: String
    @Inject("fontGame")
    private lateinit var fontGame: String
    @Inject("fontMono")
    private lateinit var fontMono: String
    @Inject("fontText")
    private lateinit var fontText: String

    private lateinit var local: LocalizationService
    private lateinit var uiFactoryService: UIFactoryService

    private lateinit var assetLoader: AssetLoaderService

    override fun onInit() {
        initAndLoadLocalization()
        initAndRegisterFontFactories()
    }

    private fun initAndLoadLocalization() {
        log.debug("Loading localizations")

        supportedLanguages.forEach {
            local.addLanguageData(it, assetLoader.loadResourceBundle("languages/${it.name.toLowerCase()}.properties"))
        }

        local.selectedLanguageProperty().bind(language)
    }

    private fun initAndRegisterFontFactories() {
        log.debug("Registering font factories with UI factory")

        val uiFactory = uiFactoryService

        uiFactory.registerFontFactory(FontType.UI, assetLoader.loadFont(fontUI))
        uiFactory.registerFontFactory(FontType.GAME, assetLoader.loadFont(fontGame))
        uiFactory.registerFontFactory(FontType.MONO, assetLoader.loadFont(fontMono))
        uiFactory.registerFontFactory(FontType.TEXT, assetLoader.loadFont(fontText))
    }
}