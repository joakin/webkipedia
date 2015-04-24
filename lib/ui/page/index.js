import React from 'react'

export default React.createClass({
  displayName: 'Page',
  propTypes: {
    params: React.PropTypes.shape({
      title: React.PropTypes.string
    })
  },
  render () {
    var title = this.props.params.title.replace(/_/g, ' ')
    return (
      <div className='Page'>
        <h1>{title}</h1>
        <h3>Working on it!</h3>
      </div>
    )
  }
})
