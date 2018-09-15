(ns re-gitea.recipes.gitea
  "Installing gitea"
  (:require-macros
   [clojure.core.strint :refer (<<)])
  (:require
   [re-conf.resources.output :refer (summary)]
   [re-conf.core :refer (map*)]
   [re-conf.resources.pkg :refer (package)]
   [re-conf.resources.user :refer (user)]
   [re-conf.resources.file :refer (chmod directory)]
   [re-conf.resources.download :refer (download)]))

(defn gitea
  "Installing gitea"
  []
  (let [url "https://dl.gitea.io/gitea/1.5.0/gitea-1.5.0-linux-amd64"
        dest "/usr/local/bin/gitea"
        parent "/var/lib/gitea/"
        folders (map (fn [f] (vector (<< "~{parent}~{f}"))) ["custom" "data" "indexers" "public" "log"])]
    (->
     (package "git")
     (download url dest)
     (directory parent :present)
     (map* directory folders)
     (chmod dest "0777")
     (summary "gitea setup"))))

(defn git-user
  "gitea user"
  []
  (->
   (user "git" :present {:system true :home "/home/git"})
   (summary "gitea user")))
