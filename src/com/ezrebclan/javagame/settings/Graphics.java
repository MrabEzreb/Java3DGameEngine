package com.ezrebclan.javagame.settings;

import java.awt.GraphicsEnvironment;

public class Graphics {

	public static int DISPLAY_WIDTH = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
	public static int DISPLAY_HEIGHT = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
	public static int FPS_CAP = 40;
	public static float FOV = 70;
	public static float NEAR_PLANE = 0.1f;
	public static float FAR_PLANE = 1000f;
}
