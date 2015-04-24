import React from 'react'
import Logo from '../logo'
import SearchBox from '../search-box'

import './top-bar.less'

export default React.createClass({
  displayName: 'TopBar',
  render () {
    return (
      <div className='TopBar'>
        <Logo />
        <SearchBox />
      </div>
    )
  }
})
