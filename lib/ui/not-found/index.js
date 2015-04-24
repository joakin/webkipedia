import React from 'react'
import {Link} from 'react-router'

export default React.createClass({
  displayName: 'NotFound',
  render () {
    return (
      <div className='NotFound'>
        <h1>Woops. Nothing here, sorry</h1>
        <h2>
          Try going to the <Link to='/'>home page</Link>
          </h2>
      </div>
    )
  }
})
