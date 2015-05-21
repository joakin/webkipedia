import Router from 'ampersand-router'
import state from './state'

let AppRouter = Router.extend({
  routes: {
    '': 'home',
    'search/': 'search',
    'search(/:q)': 'search',
    'wiki/:title': 'page'
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
  }
})
let router = new AppRouter()

router.on('route', (route) => state.cursor().set('route', route))

export default router
