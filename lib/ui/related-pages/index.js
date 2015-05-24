import React from 'react'
import component from 'omniscient'
import PageList from '../page-list'

let {div, h5} = React.DOM

export default component('RelatedPages', function (cursor) {
  let pages = cursor && cursor.get('list')
  let hasPages = pages && pages.size > 0
  return div({className: 'RelatedPages'},
    h5({}, 'Related pages'),
    hasPages ?
      PageList({ items: pages }) :
      div({}, 'Loading related articles...'))
})
