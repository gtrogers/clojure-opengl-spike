(defproject clojure-game "0.0.0-SNAPSHOT"
  ; ...
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.lwjgl.lwjgl/lwjgl "2.9.0"]
                 [org.lwjgl.lwjgl/lwjgl_util "2.9.0"]]
  :jvm-opts [~(str "-Djava.library.path=native/:"
                (System/getProperty "java.library.path"))]
  :main game.core
  :aot [game.core game.box])
