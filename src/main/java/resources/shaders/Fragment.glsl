#version 460 core

in vec3 passColor; // catching the color variable from the Vertex shader
in vec2 passTexCoord;

varying out vec4 outColor;

uniform sampler2D tex; // texture

void main() {
    // outColor = vec4(passColor, 1.0);
    outColor = texture(tex, passTexCoord);
}