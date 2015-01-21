(defproject hassuus "0.1.0-SNAPSHOT"
  :description "Find funniest words"
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.1.3"]]}
             :uberjar {:main hassuus.core
                       :aot [hassuus.core]
                       :uberjar-name "hassuus.jar"}}
  :repl-options {:init-ns hassuus.core}
  :main hassuus.core)
