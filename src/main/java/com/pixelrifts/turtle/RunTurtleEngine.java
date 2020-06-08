package com.pixelrifts.turtle;

import com.pixelrifts.turtle.engine.base.GameLayer;
import com.pixelrifts.turtle.engine.rendering.Renderer;
import com.pixelrifts.turtle.glabs.base.Application;
import com.pixelrifts.turtle.glabs.base.Display;
import com.pixelrifts.turtle.glabs.objects.Texture;
import com.pixelrifts.turtle.imgui.ImGuiLayer;
import com.pixelrifts.turtleshell.serialization.Deserializer;
import com.pixelrifts.turtleshell.serialization.SerializationFormat;
import com.pixelrifts.turtleshell.serialization.Serializer;
import org.lwjgl.opengl.GL11;

public class RunTurtleEngine {
	public static void main(String[] args) {
		Display.Init();
		Application app = new Application();
		Display.CreateWindow(app);
		GL11.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);

		ImGuiLayer imGui = new ImGuiLayer();
		imGui.Init();

		app.PushLayer(new GameLayer());
		app.PushLayer(imGui);

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;

		while (Display.IsOpen()) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			Display.PollEvents();
			while (delta >= 1) {
				app.Update((float) delta);
				updates++;
				delta--;
			}
			Renderer.Begin();
			app.Render();
			Renderer.End();

			imGui.StartFrame((float) delta);
			app.ImGuiRender();
			imGui.EndFrame();

			Display.SwapBuffers();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

		Texture.Clean();
		imGui.Terminate();

		app.PopLayer();
		app.PopLayer();

		Display.DestroyWindow();
		Display.Terminate();
	}
}