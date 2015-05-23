import React from 'react'
import component from 'omniscient'
import router from './router'
import state from './state'
import {init} from './db'
import Webkipedia from './ui/webkipedia'

React.initializeTouchEvents(true)
component.debug()

function render () {
  let cursor = state.cursor()
  console.log(cursor.toJS())
  React.render(
    Webkipedia(cursor),
    document.body
  )
}

init().then(() => {
  state.on('next-animation-frame', render)
  render()
  router.history.start({ pushState: false })
})
