(ns game.cube
  (:import [org.lwjgl.opengl ARBShaderObjects GL11]))

(defn gl3f [x y z]
  (GL11/glVertex3f x y z))

(defn draw
  ([shaderProgram]
    (ARBShaderObjects/glUseProgramObjectARB shaderProgram)
    (draw)
    (ARBShaderObjects/glUseProgramObjectARB 0))
  ([]
    (GL11/glLoadIdentity)
    (GL11/glTranslatef 0.0 0.0 -10.0)
    (GL11/glColor3f 1.0 1.0 1.0)
    (GL11/glBegin GL11/GL_QUADS)
    (gl3f -5.0 3.0 0.0)
    (gl3f 5.0 3.0 0.0)
    (gl3f 5.0 -3.0 0.0)
    (gl3f -5.0 -3.0 0.0)
    (GL11/glEnd)))
