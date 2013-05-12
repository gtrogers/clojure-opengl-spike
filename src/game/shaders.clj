(ns game.shaders
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
