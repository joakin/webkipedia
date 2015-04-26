import store from './store'
import {List} from 'immutable'
import {EventEmitter} from 'events'
import {Actions} from '../actions'
import search from '../api/search'

var currentQuery = ''
var currentSearchResults = List()

var SearchStore = store({
  getQuery: () => currentQuery,
  getSearchResults: () => currentSearchResults
})
export default SearchStore

Actions.on(Actions.SEARCH_QUERY_CHANGED, (newQuery) => {
  if (newQuery !== currentQuery) {
    currentQuery = newQuery
    SearchStore.changed()
    search(currentQuery).then((results) => {
      currentSearchResults = results
      SearchStore.changed()
    })
  }
})
