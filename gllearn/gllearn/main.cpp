#include <GL/glew.h>
#include <GLFW\glfw3.h>
#include <stdio.h>
#include <iostream>
#include <fstream>
#include <streambuf>

using namespace std;

GLuint compileShader(GLuint shaderType, string path) {
	ifstream t(path, ifstream::in);
	int filesize = t.tellg();
	char* source = (char*) malloc(filesize);
	char* iter = source;
	while (t.good()) {
		*iter = t.get();
		iter++;
	}

	GLuint s = glCreateShader(shaderType);
	glShaderSource(s, 1, &source, NULL);
	glCompileShader(s);
	return s;
}

int main() {

	if (!glfwInit()) {
		cerr << "ERROR: Could not start GLFW3";

		return 1;
	}


	GLFWwindow* window = glfwCreateWindow(1024, 768, "Hello OpenGL", NULL, NULL);
	if (!window) {
		cerr << "ERROR: Cannot create window";
		glfwTerminate();
		return 1;
	}

	glfwMakeContextCurrent(window);

	glewExperimental = GL_TRUE;
	glewInit();

	const GLubyte* renderer = glGetString(GL_RENDERER);
	const GLubyte* version = glGetString(GL_VERSION);
	cout << "Renderer: " << renderer << ", Version: " << version << endl;


	// compile shader
	// vertice shader
	GLuint vs = compileShader(GL_VERTEX_SHADER, "hellworld.vs");


	glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LESS);
	glClearColor(0, 0, 0, 1);
	while (!glfwWindowShouldClose(window)) {
    
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glfwPollEvents();
		glfwSwapBuffers(window);
	}
	glfwTerminate();
	return 0;
}