#version 330 core

in vec3 color; // catching the color variable from the Vertex shader

varying out vec4 outColor;

void main() {
    outColor = vec4(color, 1.0);
}