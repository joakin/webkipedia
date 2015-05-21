import React from 'react'
import SearchBox from '../search-box'
import SearchResults from '../search-results'
import searchApi from '../../api/search'
import {debounce} from 'lodash'
import {fromJS} from 'immutable'
import component from 'omniscient'
import router from '../../router'

import './main-page.less'

let {div, h1} = React.DOM

let search = debounce(function (query, cursor) {
  if (query) {
    // If the results are not from the current query, search
    if (query !== cursor.getIn(['searchResults', 'query'])) {
      searchApi(query).then((searchResults) => {
        // When finished searching, only set if the results query is the
        // current query
        if (searchResults.get('query') === cursor.get('searchQuery')) {
          cursor.set('searchResults', searchResults)
        }
      })
    }
  } else if (cursor.getIn(['searchResults', 'query'])) {
    // If there is no query and there are results displayed, clear the results
    cursor.set('searchResults', fromJS({query: null, results: []}))
  }
}, 200, { leading: true, maxTime: 300 })

var mixins = {
  componentWillMount () {
    search(this.props.__singleCursor.get('searchQuery'), this.props.__singleCursor)
  },
  componentWillReceiveProps (nextProps) {
    search(nextProps.__singleCursor.get('searchQuery'), nextProps.__singleCursor)
  }
}

export default component('MainPage', mixins, function (cursor) {
  let query = cursor.get('searchQuery')
  let searchingClass = Boolean(query) ? 'is-searching' : ''
  let onQueryChange = (query) => router.redirectTo(`search/${query}`)
  return div({ className: 'MainPage ' + searchingClass },
    h1({ className: 'MainPage-header' }, 'Wikipedia'),
    SearchBox({ query: query, onChange: onQueryChange }),
    SearchResults({
      items: cursor.getIn(['searchResults', 'results'])
    }))
})
