#version 460 core

// black magic
in vec3 position; // layout(location = 0) 
in vec3 color; // layout(location = 1) 
in vec2 textureCoord; // layout(location = 2) 

out vec3 passColor; // passing the color variable to the Fragment shader
out vec2 passTexCoord;

uniform mat4 model; // defaults to zero
uniform mat4 view;
uniform mat4 projection;

void main() {
    gl_Position = projection * view * model * vec4(position, 1.0);
    passColor = color; // for testing purposes
    passTexCoord = textureCoord;
}