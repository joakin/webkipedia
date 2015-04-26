import React from 'react'
import Router, {Route, DefaultRoute, NotFoundRoute} from 'react-router'
import Webkipedia from './ui/webkipedia'
import MainPageContainer from './ui/main-page/container'
import NotFound from './ui/not-found'
import Page from './ui/page'

var routes = (
  <Route handler={Webkipedia}>
    <DefaultRoute handler={MainPageContainer} />
    <Route handler={Page} name='page' path='wiki/:title' />
    <NotFoundRoute handler={NotFound} />
  </Route>
)

var router = Router.create({
  routes: routes
})
export default router
