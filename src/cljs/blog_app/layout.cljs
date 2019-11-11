(ns blog-app.layout
  (:require [blog-app.routing :refer [path-for]]
            [reagent.session :as session]
            [blog-app.articles.article-content :refer [content-store]]))
(path-for :about)
(path-for :posts)
(path-for :post)

(defn home-page []
  (fn []
    [:span.main
     [:h1.avenir "Welcome to blog-app"]
     [:ul
      [:li [:a {:href (path-for :posts)} "Items of blog-app"]]
      [:li [:a {:href "/broken/link"} "Broken link"]]]]))


(def content-list (get-in content-store [:content]))


; (defn items-page []
;   (fn []
;     [:span.main
;      [:h1 "The items of blog-app"]
;      [:ul (map (fn [content-list]
;                  [:li {:name (:title content-list) :key (str "item-" item-id)}
;                   [:a {:href (path-for :post {:post-id item-id})} "Item: " item-id]])
;                (range 1 60))]]))


(defn items-page []
  (fn []
    [:span.main
     [:h1 "The items of blog-app"]
     (for [item content-list]
       (let [kw (first item)
             data (second item)]
         ^{:key kw}
         [:div
          [:div (:title item)] 
          [:a {:href (:url data)}]]))]))


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