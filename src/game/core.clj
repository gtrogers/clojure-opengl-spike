(ns game.core
  (:require game.box)
  (:import [java.util Random]
           [org.lwjgl.opengl Display DisplayMode GL11 ARBVertexShader ARBFragmentShader]
           [org.lwjgl.util.glu GLU]))

(defn initDisplay [width, height]
  (Display/setDisplayMode (new DisplayMode width height))
  (Display/setVSyncEnabled true)
  (Display/setTitle "Shader Test")
  (Display/create))

(defn initGl []
  (initDisplay 600 400)
  (GL11/glViewport 0 0 600 400)
  (GL11/glMatrixMode GL11/GL_PROJECTION)
  (GL11/glLoadIdentity)
  (GLU/gluPerspective 45.0 (float (/ 600 400)) 0.1 100.0)
  (GL11/glMatrixMode GL11/GL_MODELVIEW)
  (GL11/glLoadIdentity)
  (GL11/glShadeModel GL11/GL_SMOOTH)
  (GL11/glClearColor 0 0 0 0)
  (GL11/glClearDepth 1.0)
  (GL11/glEnable GL11/GL_DEPTH_TEST)
  (GL11/glDepthFunc GL11/GL_LEQUAL)
  (GL11/glHint GL11/GL_PERSPECTIVE_CORRECTION_HINT GL11/GL_NICEST))

(defn render [program]
  (GL11/glClear (bit-or GL11/GL_COLOR_BUFFER_BIT GL11/GL_DEPTH_BUFFER_BIT))
  (game.box/draw program)
  (Display/update)
  (Display/sync 60))

(defn -main [& args]
  (initGl)
  (let [program (game.box/initShaders)]
    (loop [close? (Display/isCloseRequested)]
      (if-not close?
        (do
          (render program)
          (recur (Display/isCloseRequested))))))

  (Display/destroy))
