import React from 'react'
import component from 'omniscient'
import TopBar from '../top-bar'
import MainPage from '../main-page'
import Page from '../page'
import Explore from '../explore'
import MenuWrapper from '../menu-wrapper'

import './webkipedia.less'

let {div} = React.DOM

let content = {
  home: MainPage,
  search: MainPage,
  page: Page,
  explore: Explore
}

export default component('Webkipedia', function (cursor) {
  let Content = content[cursor.get('route')]
  let App =
    div({ className: 'Webkipedia' },
      TopBar(cursor.get('ui')),
      div({ className: 'Webkipedia-body container' },
        Content ? Content(cursor) : null))
  return MenuWrapper({ ui: cursor.get('ui'), menu: cursor.get('menu'), cursor: cursor }, App)
})
