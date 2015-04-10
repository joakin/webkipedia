import React from 'react'
import Router, {Route} from 'react-router'
import Webkipedia from './ui/webkipedia'

var routes = (
  <Route handler={Webkipedia} path='/' />
)

Router.run(routes, function (Handler) {
  React.render(<Handler/>, document.body)
})
