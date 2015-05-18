import React from 'react'
import SearchBox from '../search-box'
import SearchResults from '../search-results'
import {List} from 'immutable'
import search from '../../api/search'
import {debounce} from 'lodash'

import './main-page.less'

export default React.createClass({
  displayName: 'MainPage',
  contextTypes: { router: React.PropTypes.func },
  propTypes: {
    state: React.PropTypes.any
  },
  render () {
    let state = this.props.state
    let query = state.get('searchQuery')
    let searchingClass = Boolean(query) ? 'is-searching' : ''
    return (
      <div className={'MainPage ' + searchingClass}>
        <h1 className='MainPage-header'>Wikipedia</h1>
        <SearchBox query={query} onChange={this.onQueryChange}/>
        <SearchResults
          items={state.get('searchResults').deref()}
          onChange={this.onQueryChange}/>
      </div>
    )
  },
  onQueryChange (query) {
    this.updateRouteWithQuery(query)
    this.props.state.set('searchQuery', query)
    this.search(query)
  },
  search: debounce(function (query) {
    search(query).then((results) => {
      this.props.state.set('searchResults', results)
    })
  }, 200, { leading: true, maxTime: 300 }),
  updateRouteWithQuery (query) {
    this.context.router.replaceWith('/', null, query ? { q: query } : null)
  }
})
