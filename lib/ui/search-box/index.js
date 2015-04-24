import React from 'react'

import './search-box.less'

export default React.createClass({
  displayName: 'SearchBox',
  propTypes: {
    onChange: React.PropTypes.func.isRequired,
    query: React.PropTypes.string
  },
  render () {
    return (
      <div className='SearchBox'>
        <form className='SearchBox-form'>
          <input className='SearchBox-input' type='text' placeholder='Search'
            value={this.props.query} ref='query' onChange={this.queryChanged}
          />
        </form>
      </div>
    )
  },
  queryChanged () {
    this.props.onChange(this.refs.query.getDOMNode().value)
  }
})
