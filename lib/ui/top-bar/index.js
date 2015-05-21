import React from 'react'
import ButtonLink from '../button-link'
import Logo from '../logo'
import component from 'omniscient'

import './top-bar.less'

let {div} = React.DOM

export default component('TopBar', function (cursor) {
  return div({ className: 'TopBar' },
      Logo({}),
      ButtonLink({ href: '#/' }, 'Home'))
})
