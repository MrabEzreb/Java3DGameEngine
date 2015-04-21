package com.ezrebclan.javagame.java.renderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import com.ezrebclan.javagame.settings.Graphics;

public class DisplayManager {

	public static void createDisplay() throws LWJGLException {
		ContextAttribs attribs = new ContextAttribs(3, 1).withForwardCompatible(true);
		Display.create(new PixelFormat(), attribs);
		Display.setFullscreen(true);
		GL11.glViewport(0, 0, Display.getDesktopDisplayMode().getWidth(), Display.getDesktopDisplayMode().getHeight());
	}
	public static void updateDisplay() {
		Display.sync(Graphics.FPS_CAP);
		Display.update();
	}
	public static void closeDisplay() {
		Display.destroy();
	}
}
