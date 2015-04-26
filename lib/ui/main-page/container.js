import React from 'react'
import {Actions, dispatch} from '../../actions'
import MainPage from './index.js'
import SearchStore from '../../stores/search'

export default React.createClass({
  displayName: 'MainPageContainer',
  contextTypes: { router: React.PropTypes.func },
  propTypes: {
    params: React.PropTypes.object,
    query: React.PropTypes.object
  },
  getInitialState () {
    return this.getStateFromStores()
  },
  componentDidMount () {
    SearchStore.onChange(this._onChange)
    var q = this.props.query.q
    if (q) { dispatch(Actions.SEARCH_QUERY_CHANGED, q) }
  },
  componentWillUnmount () {
    SearchStore.offChange(this._onChange)
  },
  _onChange () {
    this.setState(this.getStateFromStores(), () =>
      this.context.router.replaceWith('/', null, {
        q: this.state.searchQuery
      }))
  },
  getStateFromStores () {
    return {
      searchQuery: SearchStore.getQuery(),
      searchResults: SearchStore.getSearchResults()
    }
  },
  render () {
    return (
      <MainPage searchQuery={this.state.searchQuery}
        searchResults={this.state.searchResults}/>
    )
  }
})
