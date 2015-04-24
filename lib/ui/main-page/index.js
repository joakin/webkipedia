import React from 'react'
import {debounce} from 'lodash'
import SearchBox from '../search-box'
import SearchResults from '../search-results'
import search from '../../api/search'

import './main-page.less'

export default React.createClass({
  displayName: 'MainPage',
  contextTypes: { router: React.PropTypes.func },
  propTypes: {
    params: React.PropTypes.object,
    query: React.PropTypes.object
  },
  getInitialState () {
    var q = this.props.query.q
    if (q) this.search(q)
    return {
      query: q || '',
      searchResults: []
    }
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
    this.context.router.replaceWith('/', null, { q: query })
    this.setState({ query: query })
    this.search(query)
  },
  search: debounce(function (query) {
    search(query).then((data) =>
      this.setState({ searchResults: data }))
  }, 200, { leading: true, maxTime: 300 })
})
