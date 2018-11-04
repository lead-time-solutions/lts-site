(ns lts-site.core
    (:require [reagent.core :as r]))

(enable-console-print!)


(defonce image-width (r/atom 1000))


(defonce skills ["Bespoke Software" "Scalable DevOps" "Industry Leading Solutions"])


(defn resize-handler [event]
  (->> "logo-outline"
       (.getElementsByClassName js/document)
        array-seq
        first
        .-offsetHeight
        (reset! image-width)))


(defn heading []
  [:div.heading "Lead Time Solutions"])


(defn subtitle []
  (->> skills
    (map #(vec [[:span %] [:span.skill-seperator "•"]]))
    (mapcat identity)
    butlast
    (into [:div.subtitle])))


(defn logo-outline []
  [:img.logo-outline {:src "img/lts-logo-outline.png"
                      :style {:margin-left (* -1 (/ @image-width 2))}}])


(defn logo []
  [:div.logo
    [:img {:src "img/lts-logo.png"}]])


(defn github []
  [:div.github
   [:a {:href "https://github.com/lead-time-solutions/lts-site"}
    [:img {:src "img/github-mark.svg"}]]])


(defn email []
  [:div.email
    [:a {:href "mailto:contact@leadtime.solutions?subject=Enquiry"}
        "Contact us."]])


(defn copyright []
  [:span.copyright "© Lead Time Solutions Pty Ltd"])


(defn home []
  [:div.full-page
    ;[logo-outline]
    [logo]
    [:div.content
      [github]
      [heading]
      [subtitle]
      [email]
      [copyright]]])


(defn render []
  (r/render [home]
            (.getElementById js/document "app")))


(defn ^:export main []
  (render)
  (.addEventListener js/window "resize" resize-handler))


(defn on-js-reload []
  (render))
