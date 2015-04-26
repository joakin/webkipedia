import React from 'react'
import {Link} from 'react-router'

import './logo.less'

export default React.createClass({
  displayName: 'Logo',
  render () {
    return (
      <Link className='Logo' to='/'>Wikipedia</Link>
    )
  }
})
