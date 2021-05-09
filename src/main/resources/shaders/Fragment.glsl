#version 460 core

in vec3 passColor; // catching the color variable from the Vertex shader
in vec2 passTexCoord;

out vec4 outColor;

uniform sampler2D tex; // texture
uniform int textureSample;

void main() {
    if (textureSample == 1) {
        outColor = texture(tex, passTexCoord);
    } else {
        outColor = vec4(passColor, 1.0);
    }
}