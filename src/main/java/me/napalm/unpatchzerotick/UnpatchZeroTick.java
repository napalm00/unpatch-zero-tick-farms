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

import net.fabricmc.api.ModInitializer;

import java.io.File;

public class UnpatchZeroTick implements ModInitializer {
	public static final String LOG_TAG = "Un-patch Zero Tick Farms";
	public static String modId = "unpatch-zero-tick-farms";

	private static final File configFilePath = new File("./config/"+ modId +".json");
	private static Config.ConfigBean currentConfig;

	@Override
	public void onInitialize() {
		currentConfig = new Config(configFilePath).get();
		System.out.println(LOG_TAG + ": Loaded");
	}

	public static Config.ConfigBean getConfig() {
		return currentConfig;
	}
}
