import Router from 'ampersand-router'
import state from './state'

let AppRouter = Router.extend({
  routes: {
    '': 'home',
    'search/:q': 'search',
    'wiki/:title': 'page'
  },
  home () {
    state.cursor().set('searchQuery', null)
  },
  search (q) {
    if (!q) {
      this.redirectTo('')
    } else {
      state.cursor().set('searchQuery', q)
    }
  },
  page (title) {
    state.cursor().setIn(['page', 'title'], title)
  }
})
let router = new AppRouter()

router.on('route', (route) => state.cursor().set('route', route))

export default router
