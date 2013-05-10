(ns game.box
  (:require [clojure.java.io :as io])
  (:import [org.lwjgl.opengl ARBFragmentShader ARBShaderObjects ARBVertexShader GL11]))


(defn readFileAsString [path]
  (with-open [rdr (io/reader path)]
    (clojure.string/join "\n" (line-seq rdr))))

(defn createShader [file shaderType]
  (let [shader (ARBShaderObjects/glCreateShaderObjectARB shaderType)]
    (ARBShaderObjects/glShaderSourceARB shader (readFileAsString file))
    (ARBShaderObjects/glCompileShaderARB shader)
    shader))

(defn initShaders []
  (let [vertShader (createShader "src/res/screen.vert.glsl" ARBVertexShader/GL_VERTEX_SHADER_ARB)
        fragShader (createShader "src/res/screen.frag.glsl" ARBFragmentShader/GL_FRAGMENT_SHADER_ARB)
        program (ARBShaderObjects/glCreateProgramObjectARB)]
    (ARBShaderObjects/glAttachObjectARB program vertShader)
    (ARBShaderObjects/glAttachObjectARB program fragShader)
    (ARBShaderObjects/glLinkProgramARB program)
    (print (ARBShaderObjects/glGetInfoLogARB vertShader
             (ARBShaderObjects/glGetObjectParameteriARB vertShader ARBShaderObjects/GL_OBJECT_INFO_LOG_LENGTH_ARB)))
    (print (ARBShaderObjects/glGetInfoLogARB fragShader
             (ARBShaderObjects/glGetObjectParameteriARB fragShader ARBShaderObjects/GL_OBJECT_INFO_LOG_LENGTH_ARB)))
  program))

(defn gl3f [x y z]
  (GL11/glVertex3f x y z))

(defn draw [shaderProgram]
  (ARBShaderObjects/glUseProgramObjectARB shaderProgram)
  (GL11/glLoadIdentity)
  (GL11/glTranslatef 0.0 0.0 -10.0)
  (GL11/glColor3f 1.0 1.0 1.0)
  (GL11/glBegin GL11/GL_QUADS)
  (gl3f -5.0 3.0 0.0)
  (gl3f 5.0 3.0 0.0)
  (gl3f 5.0 -3.0 0.0)
  (gl3f -5.0 -3.0 0.0)
  (GL11/glEnd)
  (ARBShaderObjects/glUseProgramObjectARB 0))
