import React from 'react'

import './search-box.less'

export default React.createClass({
  displayName: 'SearchBox',
  render () {
    return (
      <div className='SearchBox'>
        <form className='SearchBox-form'>
          <input className='SearchBox-input' type='text' placeholder='Search' />
        </form>
      </div>
    )
  }
})
