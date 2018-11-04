(defproject lts-site "0.1.0-SNAPSHOT"
  :description "Lead Time Solutions Homepage"
  :url "http://leadtime.solutions"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.7.1"

  :dependencies [[org.clojure/clojure "1.9.0-alpha17"]
                 [org.clojure/clojurescript "1.9.908"]
                 [org.clojure/core.async  "0.3.443"]
                 [reagent "0.8.0-alpha2"]
                 [garden "1.3.3"]]

  :plugins [[lein-figwheel "0.5.13"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-garden "0.3.0"]]

  :source-paths ["src"]

  :garden {:builds [{:source-paths ["src"]
           :stylesheet lts-site.styles/style
           :compiler {:output-to "resources/public/css/main.css"}}]}

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel {:on-jsload "lts-site.core/on-js-reload"
                           :open-urls ["http://localhost:3449/index.html"]}

                :compiler {:main lts-site.core
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/lts_site.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/lts_site.js"
                           :main lts-site.core
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]}
  :profiles {:dev {:dependencies [[binaryage/devtools "0.9.4"]
                                  [figwheel-sidecar "0.5.13"]
                                  [com.cemerick/piggieback "0.2.2"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
