#version 400 core

in vec2 position;

uniform mat4 transformation_matrix;
uniform mat4 projection_matrix;

out vec2 texture_coords;

void main(void){

	gl_Position = transformation_matrix * projection_matrix * vec4(position, 0.0, 1.0);
	texture_coords = vec2(position.x, -position.y);
}
