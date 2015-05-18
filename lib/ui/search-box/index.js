import React from 'react'
import {debounce} from 'lodash'
import {Actions, dispatch} from '../../actions'

import './search-box.less'

export default React.createClass({
  displayName: 'SearchBox',
  propTypes: {
    query: React.PropTypes.string,
    onChange: React.PropTypes.func
  },
  render () {
    return (
      <div className='SearchBox'>
        <form className='SearchBox-form'>
          <input className='SearchBox-input' type='text' placeholder='Search'
            value={this.props.query} ref='query' onChange={this.onQueryChange}
          />
        </form>
      </div>
    )
  },
  onQueryChange () {
    this.props.onChange(this.refs.query.getDOMNode().value)
  }
})
