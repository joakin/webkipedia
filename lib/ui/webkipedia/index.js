import React from 'react'
import component from 'omniscient'
import TopBar from '../top-bar'
import MainPage from '../main-page'
import Page from '../page'
import MenuWrapper from '../menu-wrapper'

import './webkipedia.less'

let {div} = React.DOM

let Content = {
  home: MainPage,
  search: MainPage,
  page: Page
}

export default component('Webkipedia', function (cursor) {
  let content = Content[cursor.get('route')]
  let App =
    div({ className: 'Webkipedia' },
      TopBar(cursor.get('ui')),
      div({ className: 'Webkipedia-body container' },
        content ? content(cursor) : null))
  return MenuWrapper({ ui: cursor.get('ui'), menu: cursor.get('menu') }, App)
})
