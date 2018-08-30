(ns re-gitea.recipes.main
  (:require-macros
   [clojure.core.strint :refer (<<)])
  (:require
   [re-gitea.recipes.gitea]
   [re-gitea.recipes.nginx]
   [cljs.core.async :as async :refer [take!]]
   [cljs-node-io.core :as io]
   [re-conf.resources.pkg :as pkg]
   [re-conf.resources.firewall :as fire]
   [re-conf.core :refer (invoke invoke-all report-n-exit assert-node-major-version)]
   [re-conf.resources.log :refer (info debug error)]))

(defn elk
  "Setting up only an egiteaserver instance"
  [env]
  (report-n-exit
   (invoke-all env
      re-gitea.recipes.gitea
      re-gitea.recipes.nginx)))

(defn run-profile [env profile]
  (fn [_]
    (case (keyword profile)
      :elk (elk env))))

(defn -main [e profile & args]
  (assert-node-major-version)
  (let [env (if e (cljs.reader/read-string (io/slurp e)) {})]
    (take! (async/merge [(pkg/initialize) (fire/initialize)])
           (fn [_]
             (info "Provisioning machine using re-gitea!" ::main)
             (run-profile env profile)))))

;; (set! *main-cli-fn* -main)

(comment
  (-main "resources/dev.edn" "elk"))
