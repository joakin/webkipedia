import React from 'react'
import {RouteHandler} from 'react-router'
import TopBar from '../top-bar'

import './webkipedia.less'

export default React.createClass({
  displayName: 'Webkipedia',
  render () {
    return (
      <div className='Webkipedia'>
        <TopBar/>
        <div className='Webkipedia-body container'>
          <RouteHandler {...this.props}/>
        </div>
      </div>
    )
  }
})
