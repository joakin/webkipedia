import React from 'react'
import {Actions, dispatch} from '../../actions'
import MainPage from './index.js'
import SearchStore from '../../stores/search'
import subscribe from '../mixins/subscribe-store'

export default React.createClass({
  displayName: 'MainPageContainer',
  contextTypes: { router: React.PropTypes.func },
  propTypes: {
    params: React.PropTypes.object,
    query: React.PropTypes.object
  },
  mixins: [subscribe(SearchStore)],
  componentDidMount () {
    var q = this.props.query.q
    if (q) { dispatch(Actions.SEARCH_QUERY_CHANGED, q) }
  },
  getState () {
    var query = SearchStore.getQuery()
    this.updateRouteWithQuery(query)
    return {
      searchQuery: query,
      searchResults: SearchStore.getSearchResults()
    }
  },
  updateRouteWithQuery (query) {
    this.context.router.replaceWith('/', null, query ? { q: query } : null)
  },
  render () {
    return (
      <MainPage searchQuery={this.state.searchQuery}
        searchResults={this.state.searchResults}/>
    )
  }
})
