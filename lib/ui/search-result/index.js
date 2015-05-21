import React from 'react'
import component from 'omniscient'

import './search-result.less'

let {a} = React.DOM

export default component('SearchResult', function ({item}) {
  let title = item.get('title')
  let escapedTitle = title.replace(/ /g, '_')
  return a(
    { className: 'SearchResult', href: `#/wiki/${escapedTitle}` },
    title)
})
