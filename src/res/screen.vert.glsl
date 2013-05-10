#version 120

varying vec4 vertColor;

void main(){
    vec4 v = vec4(gl_Vertex);

    gl_Position = gl_ModelViewProjectionMatrix * v;
    vertColor = vec4(v.x, v.y, v.z, 1);
}
