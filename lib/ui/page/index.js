import React from 'react'

export default React.createClass({
  displayName: 'Page',
  propTypes: {
    state: React.PropTypes.any
  },
  render () {
    var title = this.props.state.getIn(['router', 'params', 'title'], '').replace(/_/g, ' ')
    return (
      <div className='Page'>
        <h1>{title}</h1>
        <h3>Working on it!</h3>
      </div>
    )
  }
})
