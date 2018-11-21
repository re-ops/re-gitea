(ns re-gitea.recipes.gitea
  "Installing gitea"
  (:require-macros
   [clojure.core.strint :refer (<<)])
  (:require
   [re-conf.resources.output :refer (summary)]
   [re-conf.core :refer (apply*)]
   [re-conf.resources.pkg :refer (package)]
   [re-conf.resources.service :refer (service)]
   [re-conf.resources.user :refer (user group)]
   [re-conf.resources.file :refer (copy chmod chown directory)]
   [re-conf.resources.download :refer (download)]))

(defn gitea
  "Installing gitea"
  []
  (let [url "https://dl.gitea.io/gitea/1.5.0/gitea-1.5.0-linux-amd64"
        bin "/usr/local/bin/gitea"
        etc "/etc/gitea"
        lib "/var/lib/gitea/"
        folders ["custom" "data" "indexers" "public" "log"]]
    (->
     (package "git")
     (download url bin)
     (directory lib :present)
     (directory etc :present)
     (apply* directory (fn [f] (vector (<< "~{lib}~{f}"))) folders)
     (chmod bin "0777")
     (group "git" :present)
     (user "git" :present {:system true :home "/home/git"})
     (chown etc "git" "git")
     (summary "gitea setup"))))

(defn gitea-service
  "Setting up gitea service"
  []
  (->
   (copy "resources/systemd/gitea.service" "/etc/systemd/system/gitea.service")
   (service "gitea" :enable)
   (summary "gitea service")))
