import React from 'react'
import Router, {Route, DefaultRoute, NotFoundRoute} from 'react-router'
import Webkipedia from './ui/webkipedia'
import MainPage from './ui/main-page'
import NotFound from './ui/not-found'
import Page from './ui/page'

var routes = (
  <Route handler={Webkipedia}>
    <DefaultRoute handler={MainPage} />
    <Route handler={Page} name='page' path='wiki/:title' />
    <NotFoundRoute handler={NotFound} />
  </Route>
)

Router.run(routes, function (Handler, state) {
  React.render(<Handler params={state.params} query={state.query}/>, document.body)
})
