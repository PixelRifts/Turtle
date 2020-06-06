package com.pixelrifts.turtle.imgui;

import com.pixelrifts.turtle.glabs.base.Display;
import com.pixelrifts.turtle.glabs.base.Layer;
import com.pixelrifts.turtle.glabs.event.*;
import imgui.*;
import imgui.callbacks.ImStrConsumer;
import imgui.callbacks.ImStrSupplier;
import imgui.enums.ImGuiBackendFlags;
import imgui.enums.ImGuiConfigFlags;
import imgui.enums.ImGuiKey;
import imgui.enums.ImGuiMouseCursor;
import imgui.gl3.ImGuiImplGl3;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_Z;

public class ImGuiLayer extends Layer {
	private ImGuiIO io;
	private final ImGuiImplGl3 imGuiGl3 = new ImGuiImplGl3();

	private final long[] mouseCursors = new long[ImGuiMouseCursor.COUNT];
	private final int[] fbWidth = new int[1];
	private final int[] fbHeight = new int[1];
	private final int[] winWidth = new int[1];
	private final int[] winHeight = new int[1];
	private final double[] mousePosX = new double[1];
	private final double[] mousePosY = new double[1];

	public ImGuiLayer() {
		super("ImGui");
	}

	public void Init() {
		ImGui.createContext();
		io = ImGui.getIO();

		io.setIniFilename(null);
		io.setConfigFlags(ImGuiConfigFlags.NavEnableKeyboard | ImGuiConfigFlags.DockingEnable);
		io.setBackendFlags(ImGuiBackendFlags.HasMouseCursors);
		io.setBackendPlatformName("imgui_java_impl_glfw");

		ImGui.styleColorsDark();

		final int[] keyMap = new int[ImGuiKey.COUNT];
		keyMap[ImGuiKey.Tab] = GLFW_KEY_TAB;
		keyMap[ImGuiKey.LeftArrow] = GLFW_KEY_LEFT;
		keyMap[ImGuiKey.RightArrow] = GLFW_KEY_RIGHT;
		keyMap[ImGuiKey.UpArrow] = GLFW_KEY_UP;
		keyMap[ImGuiKey.DownArrow] = GLFW_KEY_DOWN;
		keyMap[ImGuiKey.PageUp] = GLFW_KEY_PAGE_UP;
		keyMap[ImGuiKey.PageDown] = GLFW_KEY_PAGE_DOWN;
		keyMap[ImGuiKey.Home] = GLFW_KEY_HOME;
		keyMap[ImGuiKey.End] = GLFW_KEY_END;
		keyMap[ImGuiKey.Insert] = GLFW_KEY_INSERT;
		keyMap[ImGuiKey.Delete] = GLFW_KEY_DELETE;
		keyMap[ImGuiKey.Backspace] = GLFW_KEY_BACKSPACE;
		keyMap[ImGuiKey.Space] = GLFW_KEY_SPACE;
		keyMap[ImGuiKey.Enter] = GLFW_KEY_ENTER;
		keyMap[ImGuiKey.Escape] = GLFW_KEY_ESCAPE;
		keyMap[ImGuiKey.KeyPadEnter] = GLFW_KEY_KP_ENTER;
		keyMap[ImGuiKey.A] = GLFW_KEY_A;
		keyMap[ImGuiKey.C] = GLFW_KEY_C;
		keyMap[ImGuiKey.V] = GLFW_KEY_V;
		keyMap[ImGuiKey.X] = GLFW_KEY_X;
		keyMap[ImGuiKey.Y] = GLFW_KEY_Y;
		keyMap[ImGuiKey.Z] = GLFW_KEY_Z;
		io.setKeyMap(keyMap);

		mouseCursors[ImGuiMouseCursor.Arrow] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.TextInput] = glfwCreateStandardCursor(GLFW_IBEAM_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeAll] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeNS] = glfwCreateStandardCursor(GLFW_VRESIZE_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeEW] = glfwCreateStandardCursor(GLFW_HRESIZE_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeNESW] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.ResizeNWSE] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);
		mouseCursors[ImGuiMouseCursor.Hand] = glfwCreateStandardCursor(GLFW_HAND_CURSOR);
		mouseCursors[ImGuiMouseCursor.NotAllowed] = glfwCreateStandardCursor(GLFW_ARROW_CURSOR);

		io.setSetClipboardTextFn(new ImStrConsumer() {
			@Override
			public void accept(final String s) {
				glfwSetClipboardString(Display.Window, s);
			}
		});

		io.setGetClipboardTextFn(new ImStrSupplier() {
			@Override
			public String get() {
				final String clipboardString = glfwGetClipboardString(Display.Window);
				if (clipboardString != null)
					return clipboardString;
				return "";
			}
		});

		final ImFontAtlas fontAtlas = io.getFonts();
		final ImFontConfig fontConfig = new ImFontConfig();

		fontConfig.setGlyphRanges(fontAtlas.getGlyphRangesCyrillic());
		fontAtlas.addFontDefault();

		fontConfig.setMergeMode(true);
		fontConfig.setPixelSnapH(true);

		fontConfig.destroy();
		ImGuiFreeType.buildFontAtlas(fontAtlas, ImGuiFreeType.RasterizerFlags.LightHinting);

		imGuiGl3.init("#version 330 core");
	}

	public void StartFrame(float deltaTime) {
		glfwGetWindowSize(Display.Window, winWidth, winHeight);
		glfwGetFramebufferSize(Display.Window, fbWidth, fbHeight);
		glfwGetCursorPos(Display.Window, mousePosX, mousePosY);

		final float scaleX = (float) fbWidth[0] / winWidth[0];
		final float scaleY = (float) fbHeight[0] / winHeight[0];

		io.setDisplaySize(fbWidth[0], fbHeight[0]);
		io.setDisplayFramebufferScale(scaleX, scaleY);
		io.setMousePos((float) mousePosX[0] * scaleX, (float) mousePosY[0] * scaleY);
		io.setDeltaTime(deltaTime);

		final int imguiCursor = ImGui.getMouseCursor();
		glfwSetCursor(Display.Window, mouseCursors[imguiCursor]);
		glfwSetInputMode(Display.Window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		ImGui.newFrame();
	}

	public void EndFrame() {
		ImGui.render();
		imGuiGl3.render(ImGui.getDrawData());
	}

	@Override
	public boolean OnLayerEvent(LayerEvent e) {
		if (e instanceof KeyPressedEvent) {
			KeyPressedEvent p = (KeyPressedEvent) e;
			io.setKeysDown(p.GetKeyCode(), true);
			io.setKeyCtrl(io.getKeysDown(GLFW_KEY_LEFT_CONTROL) || io.getKeysDown(GLFW_KEY_RIGHT_CONTROL));
			io.setKeyShift(io.getKeysDown(GLFW_KEY_LEFT_SHIFT) || io.getKeysDown(GLFW_KEY_RIGHT_SHIFT));
			io.setKeyAlt(io.getKeysDown(GLFW_KEY_LEFT_ALT) || io.getKeysDown(GLFW_KEY_RIGHT_ALT));
			io.setKeySuper(io.getKeysDown(GLFW_KEY_LEFT_SUPER) || io.getKeysDown(GLFW_KEY_RIGHT_SUPER));
		} else if (e instanceof KeyReleasedEvent) {
			KeyReleasedEvent r = (KeyReleasedEvent) e;
			io.setKeysDown(r.GetKeyCode(), false);
			io.setKeyCtrl(io.getKeysDown(GLFW_KEY_LEFT_CONTROL) || io.getKeysDown(GLFW_KEY_RIGHT_CONTROL));
			io.setKeyShift(io.getKeysDown(GLFW_KEY_LEFT_SHIFT) || io.getKeysDown(GLFW_KEY_RIGHT_SHIFT));
			io.setKeyAlt(io.getKeysDown(GLFW_KEY_LEFT_ALT) || io.getKeysDown(GLFW_KEY_RIGHT_ALT));
			io.setKeySuper(io.getKeysDown(GLFW_KEY_LEFT_SUPER) || io.getKeysDown(GLFW_KEY_RIGHT_SUPER));
		} else if (e instanceof MouseButtonEvent) {
			MouseButtonEvent m = (MouseButtonEvent) e;
			int button = m.GetButton();
			boolean press = m.IsPressed();
			io.setMouseDown(0, button == GLFW_MOUSE_BUTTON_1 && press);
			io.setMouseDown(1, button == GLFW_MOUSE_BUTTON_2 && press);
			io.setMouseDown(2, button == GLFW_MOUSE_BUTTON_3 && press);
			io.setMouseDown(3, button == GLFW_MOUSE_BUTTON_4 && press);
			io.setMouseDown(4, button == GLFW_MOUSE_BUTTON_5 && press);

			if (!io.getWantCaptureMouse() && io.getMouseDown(1)) {
				ImGui.setWindowFocus(null);
			}
		} else if (e instanceof CharTypedEvent) {
			CharTypedEvent c = (CharTypedEvent) e;
			if (c.GetCharacter() != GLFW_KEY_DELETE) {
				io.addInputCharacter(c.GetCharacter());
			}
		} else if (e instanceof MouseScrolledEvent) {
			MouseScrolledEvent s = (MouseScrolledEvent) e;
			io.setMouseWheelH(io.getMouseWheelH() + s.getXScroll());
			io.setMouseWheel(io.getMouseWheel() + s.getYScroll());
		}
		return false;
	}

	public void Terminate() {
		imGuiGl3.dispose();
		ImGui.destroyContext();
	}
}
