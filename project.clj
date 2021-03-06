(defproject webkipedia "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.145"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [cljsjs/react-with-addons "0.13.3-0"]
                 [reagent "0.5.0" :exclusions [cljsjs/react]]
                 [secretary "1.2.3"]
                 [cljs-http "0.1.37"]
                 [devcards "0.2.0-3" :exclusions [cljsjs/react]]]

  :plugins [[lein-cljsbuild "1.1.0"]
            [lein-figwheel "0.4.1"]]

  :less {:source-paths ["src/webkipedia/ui/less"]
         :target-path "resources/public/css"}

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :cljsbuild {
    :builds [{:id "devcards"
              :source-paths ["src"]
              :figwheel {:devcards true}

              :compiler {:main webkipedia.devcards.core
                         :asset-path "js/compiled/devcards_out"
                         :output-to "resources/public/js/compiled/webkipedia_devcards.js"
                         :output-dir "resources/public/js/compiled/devcards_out"
                         :source-map-timestamp true }}
             {:id "dev"
              :source-paths ["src"]

              :figwheel {:on-jsload "webkipedia.core/on-js-reload"}

              :compiler {:main webkipedia.core
                         :asset-path "js/compiled/out"
                         :output-to "resources/public/js/compiled/webkipedia.js"
                         :output-dir "resources/public/js/compiled/out"
                         :source-map-timestamp true}}
             {:id "min"
              :source-paths ["src"]
              :compiler {:output-to "resources/public/js/compiled/webkipedia.js"
                         :main webkipedia.core
                         :optimizations :advanced
                         :externs ["resources/externs/localforage.js"]
                         :pretty-print false}}]}

  :figwheel {
             ;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             :css-dirs ["resources/public/"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log" 
             })
