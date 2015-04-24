import React from 'react'

export default React.createClass({
  displayName: 'Page',
  render () {
    return (
      <div className='Page'>
        <h1>{this.props.params.title}</h1>
      </div>
    )
  }
})
