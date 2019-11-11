(ns blog-app.layout
  (:require [blog-app.routing :refer [path-for]]))
(path-for :about)
(path-for :posts)
(path-for :post)
(defn home-page []
  (fn []
    [:span
     [:h1 {:class ""} "Welcome to thee sblog-app"]
     [:ul
      [:li [:a {:href (path-for :posts)} "Items of blog-app"]]
      [:li [:a {:href "/broken/link"} "Broken link"]]]]))



(defn items-page []
  (fn []
    [:span.main
     [:h1.avenir "The items of blogs-app"]
     [:ul (map (fn [item-id]
                 [:li {:name (str "item-" item-id) :key (str "item-" item-id)}
                  [:a {:href (path-for :post {:post-id item-id})} "Item: " item-id]])
               ["a" "b"])]]))


(defn item-page []
  (fn []
    (let [routing-data (session/get :route)
          item (get-in routing-data [:route-params :post-id])]
      [:span.main
       [:h1 (str "Item " item " of blog-app")]
       [:p [:a {:href (path-for :posts)} "Back to the list of items"]]])))


(defn about-page []
  (fn [] [:span.main
          [:h1 "About blog-app"]]))