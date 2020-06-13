#version 330 core

in vec4 v_Colour;
in vec2 v_TexCoord;
in float v_TexIndex;

uniform sampler2D u_TextureSlots[16];

void main() {
    int index = int(v_TexIndex);
    gl_FragColor = texture(u_TextureSlots[index], v_TexCoord) * v_Colour;
}
