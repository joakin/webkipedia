import React from 'react'
import {Link} from 'react-router'
import Logo from '../logo'

import './top-bar.less'

export default React.createClass({
  displayName: 'TopBar',
  render () {
    return (
      <div className='TopBar'>
        <Logo />
        <Link to='/' className='button'>Home</Link>
      </div>
    )
  }
})
