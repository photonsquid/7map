package com.sevenmap.core;

import com.sevenmap.core.Map.Map;
import com.sevenmap.core.UI.UI;
import com.sevenmap.spinel.Engine;

import org.apache.commons.cli.CommandLine;

public class Runtime {
    private Engine engine;
    private UI gui;
    private Map map;

    public void load(CommandLine cl) {

        engine = new Engine();
        gui = new UI(engine);
        map = new Map();

        map.load(cl);
        gui.load(cl);

        gui.ldFileChooser();

        engine.start();
    }
}
