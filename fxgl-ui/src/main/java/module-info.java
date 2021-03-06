/*
 * FXGL - JavaFX Game Library. The MIT License (MIT).
 * Copyright (c) AlmasB (almaslvl@gmail.com).
 * See LICENSE for details.
 */

/**
 *
 */
module com.almasb.fxgl.ui {
    requires com.almasb.fxgl.animation;
    requires com.almasb.fxgl.core;
    requires com.almasb.fxgl.input;
    requires com.almasb.fxgl.localization;

    requires javafx.controls;

    exports com.almasb.fxgl.ui;

    opens com.almasb.fxgl.ui to com.almasb.fxgl.core;
}