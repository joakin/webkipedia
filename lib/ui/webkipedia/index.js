import React from 'react'
import component from 'omniscient'
import TopBar from '../top-bar'
import MainPage from '../main-page'
import Page from '../page'

import './webkipedia.less'

let {div} = React.DOM

let Content = {
  home: MainPage,
  search: MainPage,
  page: Page
}

export default component('Webkipedia', function (cursor) {
  let content = Content[cursor.get('route')]
  return div({ className: 'Webkipedia' },
      TopBar({}),
      div({ className: 'Webkipedia-body container' },
        content ? content(cursor) : null))
})
