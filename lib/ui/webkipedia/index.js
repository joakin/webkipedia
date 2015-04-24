import React from 'react'
import TopBar from '../top-bar'

import './webkipedia.less'

export default React.createClass({
  displayName: 'Webkipedia',
  render () {
    return (
      <div className='Webkipedia'>
        <TopBar />
        <div className='Webkipedia-body container'>
          <h1>Hello, world</h1>
        </div>
      </div>
    )
  }
})
