import React from 'react'
import component from 'omniscient'
import Thumbnail from '../thumbnail'

import './page-list-item.less'

let {a, span} = React.DOM

export default component('PageListItem', function ({item}) {
  let title = item.get('title')
  let escapedTitle = title.replace(/ /g, '_')
  return a(
    { className: 'PageListItem', href: `#/wiki/${escapedTitle}` },
    Thumbnail({ thumbnail: item.get('thumbnail') }),
    span({ className: 'PageListItem-title' }, title))
})
