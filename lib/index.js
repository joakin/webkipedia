import React from 'react'
import component from 'omniscient'
import router from './router'
import state from './state'
import Webkipedia from './ui/webkipedia'

component.debug()

state.on('next-animation-frame', render)
function render () {
  let cursor = state.cursor()
  console.log(cursor.toJS())
  React.render(
    Webkipedia(cursor),
    document.body
  )
}
render()
router.history.start({ pushState: false })
