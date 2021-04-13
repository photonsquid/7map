#version 330 core
// black magic
layout(location = 0) in vec3 position;

out vec3 color; // passing the color variable to the Fragment shader

void main() {
    gl_Position = vec4(position, 1.0);
    color = position; // for testing purposes
}