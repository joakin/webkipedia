import React from 'react'
import {debounce} from 'lodash'
import {Actions, dispatch} from '../../actions'

import './search-box.less'

export default React.createClass({
  displayName: 'SearchBox',
  propTypes: {
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
  queryChanged: debounce(function () {
    dispatch(Actions.SEARCH_QUERY_CHANGED, this.refs.query.getDOMNode().value)
  }, 200, { leading: true, maxTime: 300 })
})
