import featured from './api/featured'
import store from './store'
import {Actions} from '../actions'

var article = null

var FeaturedStore = store({
  getArticle: () => article
})
export default FeaturedStore

Actions.on(Actions.FEATURED_PAGE_REQUESTED, (newQuery) => {
  featured().then((data) => {
    article = data.article
    FeaturedStore.changed()
  })
})
