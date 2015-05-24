import React from 'react'
import component from 'omniscient'
import PageListItem from '../page-list-item'

import './page-list.less'

let {div} = React.DOM

export default component('PageList', function ({items}) {
  var Items = items.map((item) =>
    PageListItem({ key: item.get('title'), item: item }))
  return div({ className: 'PageList' }, Items)
})
