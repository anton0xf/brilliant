(defproject swing-drag "0.1.0-SNAPSHOT"
  :description "Example of dragging square by plane with navigation abilities"
  :url "https://github.com/anton0xf/brilliant"
  :license {:name "public domain" :url "https://unlicense.org"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [seesaw "1.5.0"] ;; https://github.com/clj-commons/seesaw
                 ]
  :main ^:skip-aot swing-drag.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
