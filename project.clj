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

      ; build
      [com.bhauman/figwheel-main "0.2.0"]
      [com.bhauman/rebel-readline-cljs "0.1.4"]
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
            [lein-npm "0.6.2"]]


  :source-paths ["src" "target"]

  :clean-targets ["server.js" "target"]

  :aliases {
     "travis" [
       "do" "clean," "cljfmt" "check," "npm" "install," "cljsbuild" "once" "prod," "cljsbuild" "test" ]
     "fig"  [
          "trampoline" "run" "-m" "figwheel.main"]
          "build-dev"  ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
  }

)
