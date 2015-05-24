import React from 'react'
import component from 'omniscient'

import './page-list-item.less'

let {a} = React.DOM

export default component('PageListItem', function ({item}) {
  let title = item.get('title')
  let escapedTitle = title.replace(/ /g, '_')
  return a(
    { className: 'PageListItem', href: `#/wiki/${escapedTitle}` },
    title)
})
