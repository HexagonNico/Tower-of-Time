#version 400 core

in vec2 texture_coords;

uniform sampler2D texture_sampler;

out vec4 final_color;

void main(void){

	final_color = texture(texture_sampler, texture_coords);

	if(final_color.a == 0){
		discard;
	}
}
