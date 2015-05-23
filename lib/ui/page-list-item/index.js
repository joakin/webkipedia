import React from 'react'
import component from 'omniscient'

let {div, a} = React.DOM

export default component('PageListItem', function (item) {
  let title = item.get('title', 'Unknown')
  return div({ className: 'PageListItem' },
    a({ className: 'PageListItem-title', href: `#/wiki/${title}` }, title))
})
