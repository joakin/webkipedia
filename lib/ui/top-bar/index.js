import React from 'react'
import ButtonLink from '../button-link'
import Logo from '../logo'
import component from 'omniscient'

import './top-bar.less'

let {div} = React.DOM

export default component('TopBar', function (cursor) {
  let openMenu = () => cursor.set('menu', true)
  return div({ className: 'TopBar' },
      Logo({}),
      ButtonLink({ onClick: openMenu }, 'Menu'))
})
