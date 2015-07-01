(ns webkipedia.ui.main-page
  (:require [webkipedia.ui.search-box :refer [search-box]]
            [webkipedia.ui.loading :refer [loading]]
            [webkipedia.state.search :refer [search]]))

(defn main-page []
  (let [{:keys [query results]} @search
        searching-class (if query "is-searching" "")]
    [:div.MainPage
     {:class searching-class}
     [:h1.MainPage-header "Wikipedia"]
     [search-box {:query query :on-change println}]
     (if loading [loading] nil)]
    ))
; export default component('MainPage', function (cursor) {
;   let query = cursor.getIn(['search', 'query'])
;   let searchingClass = Boolean(query) ? 'is-searching' : ''
;   let onQueryChange = debounce(
;     (query) => { console.log(`"${query}"`); return router.redirectTo(`search/${query}/`)},
;     200, { leading: true, maxTime: 300 }
;   )
;   let results = cursor.getIn(['search', 'results', 'list'], null)
;   let loading = query !== cursor.getIn(['search', 'results', 'query'], null)
;   return div({ className: 'MainPage ' + searchingClass },
;     h1({ className: 'MainPage-header' }, 'Wikipedia'),
;     SearchBox({ query: query, onChange: onQueryChange }),
;     loading ? Loading() : null,
;     PageList({ items: results }))
; })
