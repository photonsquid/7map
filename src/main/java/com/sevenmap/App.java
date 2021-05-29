package com.sevenmap;

import java.io.File;

import com.sevenmap.data.optiObj.LightObj;
import com.sevenmap.data.osm.Elements.Bounds.Bounds;
import com.sevenmap.data.osm.api.OSMAPI;
import com.sevenmap.exceptions.ExitOverrideException;
import com.sevenmap.spinel.Engine;
import com.sevenmap.spinel.elements.GeomNode;
import com.sevenmap.spinel.elements.Item;
import com.sevenmap.spinel.gfx.Material;
import com.sevenmap.spinel.gfx.Mesh;
import com.sevenmap.spinel.gfx.Vertex;
import com.sevenmap.spinel.math.Vector2f;
import com.sevenmap.spinel.math.Vector3f;
import com.sevenmap.spinel.scheduling.events.MoveEvent;

import org.lwjgl.glfw.GLFW;

/**
 * Main application class.
 */
public class App {

        private Engine engine = new Engine();

        // testing only ---------------------------------
        private Mesh mesh = new Mesh(new Vertex[] {
                        new Vertex(new Vector3f(-0.5f, 0.5f, 0.5f), new Vector3f(1.0f, 0.0f, 0.0f),
                                        new Vector2f(0.0f, 0.0f)), // texture
                                                                   // coordinates
                                                                   // must
                                                                   // be
                                                                   // defined
                                                                   // counter
                                                                   // clockwise
                        new Vertex(new Vector3f(0.5f, 0.5f, 0.5f), new Vector3f(0.0f, 1.0f, 0.0f),
                                        new Vector2f(0.0f, 1.0f)),
                        new Vertex(new Vector3f(0.5f, -0.5f, 0.5f), new Vector3f(1.0f, 0.0f, 0.0f),
                                        new Vector2f(1.0f, 1.0f)),
                        new Vertex(new Vector3f(-0.5f, -0.5f, 0.5f), new Vector3f(0.0f, 0.0f, 1.0f),
                                        new Vector2f(1.0f, 0.0f)),
                        new Vertex(new Vector3f(-0.5f, 0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f),
                                        new Vector2f(0.0f, 0.0f)),
                        new Vertex(new Vector3f(0.5f, 0.5f, -0.5f), new Vector3f(0.0f, 1.0f, 0.0f),
                                        new Vector2f(0.0f, 1.0f)),
                        new Vertex(new Vector3f(0.5f, -0.5f, -0.5f), new Vector3f(1.0f, 0.0f, 0.0f),
                                        new Vector2f(1.0f, 1.0f)),
                        new Vertex(new Vector3f(-0.5f, -0.5f, -0.5f), new Vector3f(0.0f, 0.0f, 1.0f),
                                        new Vector2f(1.0f, 0.0f))

        },

                        new int[] { 0, 1, 2, 0, 3, 2, 4, 5, 6, 4, 7, 6 }, new Material("textures/logo.png"));

        private Item testElement = new Item(new Vector3f(0, 0, -1.0f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1),
                        mesh);
        private Item testElement2 = new Item(new Vector3f(0, 0, -4.0f), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1),
                        mesh);

        public static Logger logger = null;

        public void start() {
                init();
        }

        private GeomNode parentTestNode = new GeomNode(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));
        private GeomNode parentTestNode2 = new GeomNode(new Vector3f(0, 0, 0), new Vector3f(0, 0, 0));

        // testing only ---------------------------------

        /**
         * App boot up sequence.
         */
        public void init() {

                // test out node structure
                testElement.setParent(parentTestNode);
                testElement2.setParent(parentTestNode);
                parentTestNode.setParent(engine.getSceneRoot());
                parentTestNode2.setParent(testElement);
                engine.getSceneRoot().tree();

                // schedule movement macros
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_A,
                                () -> engine.getCamera().setPos(engine.getCamera().getPos().getX() - 0.05f,
                                                engine.getCamera().getPos().getY(),
                                                engine.getCamera().getPos().getZ()));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_D,
                                () -> engine.getCamera().setPos(engine.getCamera().getPos().getX() + 0.05f,
                                                engine.getCamera().getPos().getY(),
                                                engine.getCamera().getPos().getZ()));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_W,
                                () -> engine.getCamera().setPos(engine.getCamera().getPos().getX(),
                                                engine.getCamera().getPos().getY(),
                                                engine.getCamera().getPos().getZ() - 0.05f));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_S,
                                () -> engine.getCamera().setPos(engine.getCamera().getPos().getX(),
                                                engine.getCamera().getPos().getY(),
                                                engine.getCamera().getPos().getZ() + 0.05f));

                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_E,
                                () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                                engine.getCamera().getRot().getY(),
                                                engine.getCamera().getRot().getZ() + 1f));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_Q,
                                () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                                engine.getCamera().getRot().getY(),
                                                engine.getCamera().getRot().getZ() - 1f));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_UP,
                                () -> engine.getCamera().setRot(engine.getCamera().getRot().getX() + 1f,
                                                engine.getCamera().getRot().getY(),
                                                engine.getCamera().getRot().getZ()));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_DOWN,
                                () -> engine.getCamera().setRot(engine.getCamera().getRot().getX() - 1f,
                                                engine.getCamera().getRot().getY(),
                                                engine.getCamera().getRot().getZ()));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_LEFT,
                                () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                                engine.getCamera().getRot().getY() + 1f,
                                                engine.getCamera().getRot().getZ()));
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_RIGHT,
                                () -> engine.getCamera().setRot(engine.getCamera().getRot().getX(),
                                                engine.getCamera().getRot().getY() - 1f,
                                                engine.getCamera().getRot().getZ()));

                // schedule engine.getWindow() closing when escape is pressed
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_ESCAPE, () -> {
                        throw new ExitOverrideException(0);
                });

                // fullscreen
                engine.getWindow().onKeyDown(GLFW.GLFW_KEY_F11,
                                () -> engine.getWindow().setFullscreen(!engine.getWindow().isFullscreen()));

                // event showcase
                engine.getWindow().onEvent(new MoveEvent(engine),
                                () -> parentTestNode.setRot(testElement.getRot().add(new Vector3f(0, 0, 0.3f))));

                engine.start();
        }

        public static void main(String[] args) {
                // Create OSM Map
                Bounds N7Bounds = new Bounds(1.45338, 43.60116, 1.45760, 43.60297);
                File n7Map = new File("src/main/resources/maps/osm/n7.osm");
                OSMAPI OSMMap = new OSMAPI(N7Bounds, n7Map);

                // Download new map
                OSMMap.downloadMap();

                // Parse data
                OSMMap.parse();

                // Convert into optimized files
                LightObj ltObj = new LightObj(OSMMap);

                //
                new App().start();
        }

}
