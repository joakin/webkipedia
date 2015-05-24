import Router from 'ampersand-router'
import state from './state'
import {List} from 'immutable'

import random from './api/random'
import article from './api/article'
import search from './api/search'
import {empty as emptySearch} from './state/search'

let AppRouter = Router.extend({
  routes: {
    '': 'home',
    'search/': 'search',
    'search(/:q)': 'search',
    'wiki/:title': 'page',
    'explore': 'explore',
    'explore?:date': 'exploreHash'
  },
  home () {
    state.cursor().setIn(['search', 'query'], null)
  },
  search (q) {
    if (!q) {
      this.redirectTo('')
    } else {
      state.cursor().setIn(['search', 'query'], q)
      if (q) {
        // If the results are not from the current query, search
        if (q !== state.cursor().getIn(['search', 'results', 'query'])) {
          search(q).then((searchResults) => {
            console.log(searchResults)
            // When finished searching, only set if the results query is the
            // current query
            if (searchResults.get('query') === state.cursor().getIn(['search', 'query'])) {
              state.cursor().setIn(['search', 'results'], searchResults)
            }
          })
        }
      } else if (state.cursor().getIn(['search', 'results', 'query'])) {
        // If there is no query and there are results displayed, clear the results
        state.cursor().setIn(['search', 'results'], emptySearch.get('results'))
      }
    }
  },
  page (title) {
    state.cursor().setIn(['page', 'title'], title)
    // If the results are not loaded, then request them
    if (title && title !== state.cursor().getIn(['page', 'content', 'title'])) {
      state.cursor().setIn(['page', 'content'], null)
      article(title).then(result => {
        console.log(result)
        // If the result is relevant for the current page, swap it
        if (result.get('title') === state.cursor().getIn(['page', 'title'])) {
          state.cursor().setIn(['page', 'content'], result)
        }
      })
    }
  },
  explore () {
    state.cursor().update((state) =>
      state
        .setIn(['ui', 'menu'], false)
        .set('explore', List()))
    random(60).then((results) => state.cursor().set('explore', results))
    this.navigate('explore?' + Date.now(), {trigger: false})
  }
})
let router = new AppRouter()

router.on('route', (route) => {
  state.cursor().update((state) =>
    state
      .set('route', route)
      .setIn(['ui', 'menu'], false))
})

export default router
