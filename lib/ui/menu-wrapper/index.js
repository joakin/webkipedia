import React from 'react'
import component from 'omniscient'
import Menu from '../menu'

import './menu-wrapper.less'

let {div} = React.DOM

export default component('MenuWrapper', function ({ui, menu}) {
  let dismissMenu = () => ui.set('menu', false)
  let App = this.props.children
  let open = ui.get('menu', false)
  return div.apply(null, [
    { className: 'MenuWrapper ' + (open ? 'is-open' : '') },
    Menu(menu),
    div({
      className: 'MenuWrapper-overlay',
      onTouchStart: dismissMenu,
      onClick: dismissMenu
    }, null)
  ].concat(App))
})
