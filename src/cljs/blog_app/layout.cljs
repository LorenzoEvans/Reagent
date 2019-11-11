(ns blog-app.layout
  (:require [blog-app.articles.article-content :refer [content-store]]))

(defn home-page []
  (fn []
    [:span
     [:h1 {:class} "Welcome to thee sblog-app"]
     [:ul
      [:li [:a {:href (path-for :items)} "Items of blog-app"]]
      [:li [:a {:href "/broken/link"} "Broken link"]]]]))



(defn posts-page []
  (fn []
    [:span.main
     [:h1.avenir "The items of blog-app"]
     [:ul (map (fn [item-id]
                 [:li {:name (str "item-" item-id) :key (str "item-" item-id)}
                  [:a {:href (path-for :item {:item-id item-id})} "Item: " item-id]])
               (range 1 60))]]))


(defn post-page []
  (fn []
    (let [routing-data (session/get :route)
          item (get-in routing-data [:route-params :item-id])]
      [:span.main
       [:h1 (str "Item " item " of blog-app")]
       [:p [:a {:href (path-for :items)} "Back to the list of items"]]])))


(defn about-page []
  (fn [] [:span.main
          [:h1 "About blog-app"]]))