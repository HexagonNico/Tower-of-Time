#version 400 core

in vec2 texture_coords;

uniform sampler2D texture_sampler;

uniform vec2 map_size;
uniform int[400] tilemap;
uniform int[400] tilemap_overlay;

out vec4 final_color;

void thing(int[400] tiles){

	//Get the index of the tile we need from tilemap
	vec2 tiled_coords = floor(texture_coords * map_size);
	int index = tiles[int(tiled_coords.y * map_size.x + tiled_coords.x)];

	//Get texture size
	ivec2 tileset_texture_size = textureSize(texture_sampler, 0) / 16;

	//Get UV of tile in tileset
	vec2 coords_in_tileset = vec2(index % tileset_texture_size.x, index / tileset_texture_size.x);

	//Get offset by getting decimal part
	vec2 offset = fract(texture_coords * map_size) / tileset_texture_size;

	final_color = texture(texture_sampler, coords_in_tileset / tileset_texture_size + offset);
}

void main(void){

	thing(tilemap_overlay);

	if(final_color.a == 0)
		thing(tilemap);
}
