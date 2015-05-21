import React from 'react'
import component from 'omniscient'
import SearchResult from '../search-result'

import './search-results.less'

let {div} = React.DOM

export default component('SearchResults', function ({items}) {
  var Items = items.map((item) =>
    SearchResult({ key: item.get('title'), item: item }))
  return div({ className: 'SearchResults' }, Items)
})
