import React from 'react'
import router from './router'

router.run(function (Handler, state) {
  React.render(
    <Handler params={state.params} query={state.query}/>,
    document.body
  )
})
