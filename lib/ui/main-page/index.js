import React from 'react'
import SearchBox from '../search-box'
import SearchResults from '../search-results'
import searchApi from '../../api/search'
import {debounce} from 'lodash'
import {fromJS} from 'immutable'
import component from 'omniscient'
import router from '../../router'
import {empty as emptySearch} from './search'

import './main-page.less'

let {div, h1} = React.DOM

let search = debounce(function (query, cursor) {
  if (query) {
    // If the results are not from the current query, search
    if (query !== cursor.getIn(['search', 'results', 'query'])) {
      searchApi(query).then((searchResults) => {
        // When finished searching, only set if the results query is the
        // current query
        if (searchResults.get('query') === cursor.getIn(['search', 'query'])) {
          cursor.setIn(['search', 'results'], searchResults)
        }
      })
    }
  } else if (cursor.getIn(['search', 'results', 'query'])) {
    // If there is no query and there are results displayed, clear the results
    cursor.setIn(['search', 'results'], fromJS(emptySearch.get('results')))
  }
}, 200, { leading: true, maxTime: 300 })

var mixins = {
  componentWillMount () {
    search(this.props.__singleCursor.getIn(['search', 'query']), this.props.__singleCursor)
  },
  componentWillReceiveProps (nextProps) {
    search(nextProps.__singleCursor.getIn(['search', 'query']), nextProps.__singleCursor)
  }
}

export default component('MainPage', mixins, function (cursor) {
  let query = cursor.getIn(['search', 'query'])
  let searchingClass = Boolean(query) ? 'is-searching' : ''
  let onQueryChange = (query) => router.redirectTo(`search/${query}`)
  return div({ className: 'MainPage ' + searchingClass },
    h1({ className: 'MainPage-header' }, 'Wikipedia'),
    SearchBox({ query: query, onChange: onQueryChange }),
    SearchResults({
      items: cursor.getIn(['search', 'results', 'list'])
    }))
})
