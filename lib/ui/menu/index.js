import React from 'react'
import component from 'omniscient'
import {fromJS} from 'immutable'
import MenuItem from '../menu-item'

import './menu.less'

let {div, ul} = React.DOM

export default component('Menu', function (menu) {
  let Items = menu.map((item) => MenuItem(item))
  return div({ className: 'Menu' },
    ul({ className: 'Menu-list' },
      Items))
})
