/*
    Copyright 2021 naPalm (hello@napalm.me)

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
    to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
    and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
    INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
    IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
    TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package me.napalm.unpatchzerotick;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private File configFilePath;

    public Config(File configFilePath) {
        this.configFilePath = configFilePath;
    }

    public static class ConfigBean {
        public boolean isZeroTickUnpatchEnabled = true;
        public boolean isBambooZeroTickEnabled = true;
        public boolean isCactusZeroTickEnabled = true;
        public boolean isChorusFlowerZeroTickEnabled = true;
        public boolean isSugarCaneZeroTickEnabled = true;
        public boolean isVinesZeroTickEnabled = true;
    }

    private ConfigBean currentConfig = null;

    public ConfigBean get() {
        if(currentConfig != null) {
            return currentConfig;
        }

        if(configFilePath.exists()) {
            currentConfig = this.load();
        }

        if(currentConfig != null) {
            return currentConfig;
        }

        currentConfig = new ConfigBean();

        this.writeConfigFile(currentConfig);
        return currentConfig;
    }

    private ConfigBean load() {
        ConfigBean currentConfig = this.readConfigFile(configFilePath);
        if (currentConfig != null) {
            return currentConfig;
        }
        return null;
    }

    public ConfigBean readConfigFile(File path) {
        try (FileReader file = new FileReader(path)) {
            Gson gson = new Gson();
            return gson.fromJson(file, ConfigBean.class);
        }
        catch (IOException e) {
            System.err.println(UnpatchZeroTick.LOG_TAG + ": Failed to read from config.");
            return null;
        }
    }

    public void writeConfigFile(ConfigBean config) {
        if (!configFilePath.getParentFile().exists() && !configFilePath.getParentFile().mkdirs()) {
            System.err.println(UnpatchZeroTick.LOG_TAG + ": Failed to write the config.");
            return;
        }
        try (FileWriter file = new FileWriter(configFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            file.write(gson.toJson(config));
            file.flush();
        }
        catch (IOException e) {
            System.err.println(UnpatchZeroTick.LOG_TAG + ": Failed to write the config.");
        }
    }
}
