import React from 'react'
import SearchResult from '../search-result'

import './search-results.less'

export default React.createClass({
  displayName: 'SearchResults',
  propTypes: {
    items: React.PropTypes.array
  },
  render () {
    var items = this.props.items.map((item) => <SearchResult {...item}/>)
    return (
      <div className='SearchResults'>
        {items}
      </div>
    )
  }
})
