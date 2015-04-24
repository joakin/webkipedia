import React from 'react'
import Logo from '../logo'

import './top-bar.less'

export default React.createClass({
  displayName: 'TopBar',
  render () {
    return (
      <div className='TopBar'>
        <Logo />
        <span>Search box</span>
      </div>
    )
  }
})
