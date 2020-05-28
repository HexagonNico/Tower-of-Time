#version 400 core

in vec2 position;

uniform mat4 transformation_matrix;
uniform mat4 projection_matrix;
uniform mat4 view_matrix;

uniform vec2 atlas_size;
uniform vec2 uv;

out vec2 texture_coords;

void animation(void){
	texture_coords = texture_coords / atlas_size;
	texture_coords = texture_coords + (1 / atlas_size) * uv;
}

void main(void){

	gl_Position = projection_matrix * view_matrix * transformation_matrix * vec4(position, 0.0, 1.0);
	texture_coords = vec2(position.x + 0.5, 0.5 - position.y);

	animation();
}
