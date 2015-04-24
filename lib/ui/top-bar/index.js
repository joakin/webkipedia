import React from 'react'
import ButtonLink from '../button-link'
import Logo from '../logo'

import './top-bar.less'

export default React.createClass({
  displayName: 'TopBar',
  render () {
    return (
      <div className='TopBar'>
        <Logo />
        <ButtonLink to='/'>Home</ButtonLink>
      </div>
    )
  }
})
