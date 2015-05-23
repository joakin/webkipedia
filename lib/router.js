import Router from 'ampersand-router'
import state from './state'
import random from './api/random'
import {List} from 'immutable'

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
    }
  },
  page (title) {
    state.cursor().setIn(['page', 'title'], title)
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
