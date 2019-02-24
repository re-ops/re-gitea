(defproject re-gitea "0.1.0"
  :description "Setting up Gitea using Re-conf"
  :url "https://github.com/re-ops/re-gitea"
  :license  {:name "Apache License, Version 2.0" :url "http://www.apache.org/licenses/LICENSE-2.0.html"}

  :min-lein-version "2.7.1"

  :dependencies [
      [org.clojure/clojure "1.10.0"]

      ; clojurescript
      [org.clojure/clojurescript "1.10.516"]

      ; << macro
      [org.clojure/core.incubator "0.1.4"]

      [re-conf "0.4.4"]
   ]

   :npm {
        :dependencies [
          ["request" "2.85.0"]
        ]

        :devDependencies[
          ["source-map-support" "^0.4.15"]
          ["ws" "^0.8.1"]
          ["winston" "3.0.0-rc3"]
          ["systeminformation" "3.37.9"]
        ]
   }

  :plugins [[lein-cljfmt "0.5.7"]
            [lein-tag "0.1.0"]
            [lein-npm "0.6.2"]
            [cider/cider-nrepl "0.18.0"] ]

  :profiles  {:dev
      {:dependencies  [[org.clojure/clojurescript "1.10.339"]
                       [com.bhauman/figwheel-main "0.2.0"]
                       [cider/piggieback "0.3.8"]]
        :resource-paths  ["target"]
            :clean-targets ^{:protect false}  ["target"]
            :repl-options  {:nrepl-middleware  [cider.piggieback/wrap-cljs-repl]}}}

  :source-paths ["src" "target"]

  :clean-targets ["server.js" "target"]

  :aliases {
     "travis" [
       "do" "clean," "cljfmt" "check," "npm" "install," "cljsbuild" "once" "prod," "cljsbuild" "test" ]

     "fig" ["trampoline" "run" "-m" "figwheel.main"]
  }

)
