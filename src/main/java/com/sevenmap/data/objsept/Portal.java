package com.sevenmap.data.objsept;

import java.util.ArrayList;
import java.util.List;

import com.sevenmap.spinel.elements.geom.Item;
import com.sevenmap.spinel.gfx.Mesh;
import com.sevenmap.spinel.gfx.Vertex;
import com.sevenmap.spinel.math.Vector3f;
import com.sevenmap.spinel.utils.Color;

public class Portal {

        public static Item loadRoad(List<Vector3f> roadData) {
                float roadWidth = 0.1f;
                float borderWidth = 0.05f; // fixed atm, should be deduced from road importance

                Color roadColor = new Color(255, 255, 255);
                Color borderColor = new Color(255, 0, 0);

                Vector3f position = new Vector3f(0, 0, 0);
                Vector3f rotation = new Vector3f(0, 0, 0);
                Vector3f scale = new Vector3f(1, 1, 1);

                List<Vertex> vertices = new ArrayList<>(); // center quad + 2 border quads
                List<Integer> indices = new ArrayList<>(); // 6 indices per quad, 3 * (roadData.size() - 1) quads

                Vector3f segment = new Vector3f(0, 0, 0);
                for (int i = 0; i < roadData.size() - 1; i++) {
                        // directional vector
                        segment = new Vector3f(roadData.get(i + 1).sub(roadData.get(i)));
                        float a = (float) Math.sqrt(segment.getZ() * segment.getZ()
                                        / (segment.getX() * segment.getX() + segment.getZ() * segment.getZ()));
                        float b = 0;
                        float c = (float) -Math.sqrt(segment.getX() * segment.getX()
                                        / (segment.getX() * segment.getX() + segment.getZ() * segment.getZ()));
                        // orthogonal vector (normalized)
                        Vector3f lateral = new Vector3f(a, b, c);

                        // create vertices
                        // - left border
                        vertices.add(new Vertex(roadData.get(i).add(lateral.multiply(roadWidth / 2 + borderWidth)),
                                        borderColor));
                        // - left roadside
                        vertices.add(new Vertex(roadData.get(i).add(lateral.multiply(roadWidth / 2)), roadColor));
                        // - right roadside
                        vertices.add(new Vertex(roadData.get(i).add(lateral.multiply(-roadWidth / 2)), roadColor));
                        // - right border
                        vertices.add(new Vertex(roadData.get(i).add(lateral.multiply(-roadWidth / 2 - borderWidth)),
                                        borderColor));

                        // left border quad
                        indices.add(4 * i);
                        indices.add(4 * i + 4);
                        indices.add(4 * i + 5);
                        indices.add(4 * i);
                        indices.add(4 * i + 1);
                        indices.add(4 * i + 5);

                        // road quad
                        indices.add(4 * i + 1);
                        indices.add(4 * i + 2);
                        indices.add(4 * i + 5);
                        indices.add(4 * i + 2);
                        indices.add(4 * i + 5);
                        indices.add(4 * i + 6);

                        // right border quad
                        indices.add(4 * i + 2);
                        indices.add(4 * i + 3);
                        indices.add(4 * i + 6);
                        indices.add(4 * i + 3);
                        indices.add(4 * i + 6);
                        indices.add(4 * i + 7);
                }

                // append last vertices
                float a = (float) Math.sqrt(segment.getZ() * segment.getZ()
                                / (segment.getX() * segment.getX() + segment.getZ() * segment.getZ()));
                float b = 0;
                float c = (float) -Math.sqrt(segment.getX() * segment.getX()
                                / (segment.getX() * segment.getX() + segment.getZ() * segment.getZ()));
                // orthogonal vector (normalized)
                Vector3f lateral = new Vector3f(a, b, c);
                vertices.add(new Vertex(
                                roadData.get(roadData.size() - 1).add(lateral.multiply(roadWidth / 2 + borderWidth)),
                                borderColor)); // left border
                vertices.add(new Vertex(roadData.get(roadData.size() - 1).add(lateral.multiply(roadWidth / 2)),
                                roadColor)); // left
                                             // roadside
                vertices.add(new Vertex(roadData.get(roadData.size() - 1).add(lateral.multiply(-roadWidth / 2)),
                                roadColor)); // right
                                             // roadside
                vertices.add(new Vertex(
                                roadData.get(roadData.size() - 1).add(lateral.multiply(-roadWidth / 2 - borderWidth)),
                                borderColor)); // right border

                // create mesh and Item
                Mesh roadMesh = new Mesh(vertices, indices);
                Item roadItem = new Item(position, rotation, scale, roadMesh);
                return roadItem;
        }

}
