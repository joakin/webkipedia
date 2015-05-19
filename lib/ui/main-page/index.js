import React from 'react'
import SearchBox from '../search-box'
import SearchResults from '../search-results'
import {List} from 'immutable'
import search from '../../api/search'
import {debounce} from 'lodash'
import {fromJS} from 'immutable'

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
  componentWillReceiveProps (nextProps) {
    this.search(this.getCurrentQuery(nextProps), nextProps)
  },
  render () {
    let state = this.props.state
    let query = this.getCurrentQuery()
    let searchingClass = Boolean(query) ? 'is-searching' : ''
    return (
      <div className={'MainPage ' + searchingClass}>
        <h1 className='MainPage-header'>Wikipedia</h1>
        <SearchBox query={query} onChange={this.onQueryChange}/>
        <SearchResults
          items={state.get('searchResults').deref().get('results')}
          onChange={this.onQueryChange}/>
      </div>
    )
  },
  getCurrentQuery (props) { return (props || this.props).state.getIn(['router', 'query', 'q']) },
  onQueryChange (query) {
    this.updateRouteWithQuery(query)
  },
  search: debounce(function (query, props) {
    props = props || this.props
    if (query) {
      if (query !== props.state.getIn(['searchResults', 'query'])) {
        search(query).then((searchResults) => {
          if (searchResults.get('query') === this.getCurrentQuery()) {
            props.state.set('searchResults', searchResults)
          }
        })
      }
    } else if (props.state.getIn(['searchResults', 'query'])) {
      props.state.set('searchResults', fromJS({query: null, results: []}))
    }
  }, 200, { leading: true, maxTime: 300 }),
  updateRouteWithQuery (query) {
    this.context.router.replaceWith('/', null, query ? { q: query } : null)
  }
})
