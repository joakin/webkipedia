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
  componentWillMount () {
    this.search(this.getCurrentQuery())
  },
  render () {
    let state = this.props.state
    let query = this.getCurrentQuery()
    console.log(query)
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
  getCurrentQuery () { return this.props.state.getIn(['router', 'query', 'q']) },
  onQueryChange (query) {
    this.updateRouteWithQuery(query)
    this.search(query)
  },
  search: debounce(function (query) {
    if (query) {
      search(query).then((results) => {
        this.props.state.set('searchResults', results)
      })
    } else {
      this.props.state.set('searchResults', List())
    }
  }, 200, { leading: true, maxTime: 300 }),
  updateRouteWithQuery (query) {
    this.context.router.replaceWith('/', null, query ? { q: query } : null)
  }
})
