package com.nopearlmomentum;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoPearlMomentumMod implements ModInitializer {
	public static final String MOD_ID = "nopearlmomentum";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("No Pearl Momentum loaded: ender pearls ignore shooter velocity.");
	}
}
