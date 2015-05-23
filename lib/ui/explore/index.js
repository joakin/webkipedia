import React from 'react'
import component from 'omniscient'
import random from '../../api/random'
import PageList from '../page-list'

let {div, h3} = React.DOM

export default component('Explore', function (cursor) {
  let pages = cursor.get('explore')
  if (pages) {
    return div({className: 'Explore'},
      h3({}, 'Explore'),
      PageList(pages))
  } else {
    return div({}, 'Loading articles...')
  }
})
