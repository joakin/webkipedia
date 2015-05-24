import React from 'react'
import component from 'omniscient'
import PageList from '../page-list'

let {div, h3} = React.DOM

export default component('Explore', function (cursor) {
  let pages = cursor.get('explore')
  let hasPages = pages && pages.size > 0
  return div({className: 'Explore'},
    h3({}, 'Explore'),
    hasPages ?
      PageList({ items: pages }) :
      div({}, 'Loading articles...'))
})
