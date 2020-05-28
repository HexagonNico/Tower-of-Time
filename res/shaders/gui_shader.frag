#version 400 core

in vec2 texture_coords;

uniform sampler2D texture_sampler;

uniform vec2 texture_size;
uniform vec2 uv;

out vec4 final_color;

void main(void){

	ivec2 pixel_texture_size = textureSize(texture_sampler, 0);

	final_color = texture(texture_sampler, texture_coords * (texture_size / pixel_texture_size) + (uv / pixel_texture_size));

	if(final_color.a == 0){
		discard;
	}
}
