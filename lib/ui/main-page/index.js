import React from 'react'
import {debounce} from 'lodash'
import SearchBox from '../search-box'
import SearchResults from '../search-results'
import search from '../../api/search'

import './main-page.less'

export default React.createClass({
  displayName: 'MainPage',
  getInitialState () {
    return { query: '', searchResults: [] }
  },
  render () {
    var searchingClass = Boolean(this.state.query) ? 'is-searching' : ''
    return (
      <div className={'MainPage ' + searchingClass}>
        <h1 className='MainPage-header'>Wikipedia</h1>
        <SearchBox query={this.state.query} onChange={this.onQueryChange}/>
        <SearchResults items={this.state.searchResults}/>
      </div>
    )
  },
  onQueryChange (query) {
    this.setState({ query: query })
    this.search(query)
  },
  search: debounce(function (query) {
    search(query).then((data) =>
      this.setState({ searchResults: data }))
  }, 200, { leading: true, maxTime: 300 })
})
