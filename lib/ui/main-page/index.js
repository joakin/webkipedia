import React from 'react'
import SearchBox from '../search-box'
import SearchResults from '../search-results'
import {List} from 'immutable'

import './main-page.less'

export default React.createClass({
  displayName: 'MainPage',
  contextTypes: { router: React.PropTypes.func },
  propTypes: {
    searchQuery: React.PropTypes.string,
    searchResults: React.PropTypes.instanceOf(List)
  },
  render () {
    var searchingClass = Boolean(this.props.searchQuery) ? 'is-searching' : ''
    return (
      <div className={'MainPage ' + searchingClass}>
        <h1 className='MainPage-header'>Wikipedia</h1>
        <SearchBox query={this.props.searchQuery}/>
        <SearchResults items={this.props.searchResults}/>
      </div>
    )
  }
})
