import React from 'react'
import component from 'omniscient'
import PageListItem from '../page-list-item'

import './page-list.less'

let {div} = React.DOM

export default component('PageList', function (items) {
  return div({ className: 'PageList' },
    items.map((item) => PageListItem(item)))
})
