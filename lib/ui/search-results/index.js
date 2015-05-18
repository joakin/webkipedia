import React from 'react'
import SearchResult from '../search-result'
import {List} from 'immutable'

import './search-results.less'

export default React.createClass({
  displayName: 'SearchResults',
  propTypes: {
    items: React.PropTypes.instanceOf(List)
  },
  render () {
    var items = this.props.items.map((item) =>
      <SearchResult key={item.get('title')} item={item}/>)
    return (<div className='SearchResults'>{items}</div>)
  }
})
