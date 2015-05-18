import React from 'react'
import Immutable from 'immutable'
import Cursor from 'immutable/contrib/cursor'
import router from './router'
import {cursor, initialState} from './state'

var appState = cursor(initialState, render)

function render (newState) {
  appState = newState
  let Handler = appState.get('Handler')
  if (Handler) {
    React.render(
      <Handler state={appState}/>,
      document.body
    )
  }
}

router.run(function (Handler, routerState) {
  appState.withMutations(state => {
    state
      .set('Handler', Handler)
      .set('router', Immutable.fromJS(routerState))
  })
})
