package com.ezrebclan.javagame.java;

import org.lwjgl.LWJGLException;

import com.ezrebclan.javagame.java.renderEngine.DisplayManager;
import com.ezrebclan.javagame.java.renderEngine.EntityRenderer;
import com.ezrebclan.javagame.java.renderEngine.Loader;
import com.ezrebclan.javagame.shaders.StaticShader;

@Deprecated
public abstract class GameLoop {

	public Loader loader = new Loader();
	public EntityRenderer renderer;
	public void startGameLoop() {
		loop = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					DisplayManager.createDisplay();
				} catch (LWJGLException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				renderer = new EntityRenderer(getShader());
				init();
				while(shouldRun()) {
					getLoop().run();
				}
				exit();
				DisplayManager.closeDisplay();
			}
		});
		loop.start();
	}
	public abstract Runnable getLoop();
	private Thread loop;
	public abstract boolean shouldRun();
	public abstract void init();
	public abstract void exit();
	public abstract StaticShader getShader();
}
