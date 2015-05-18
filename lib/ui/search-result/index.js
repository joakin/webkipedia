import React from 'react'
import {Link} from 'react-router'
import {Map} from 'immutable'

import './search-result.less'

export default React.createClass({
  displayName: 'SearchResult',
  propTypes: {
    item: React.PropTypes.instanceOf(Map)
  },
  render () {
    let title = this.props.item.get('title')
    let escapedTitle = title.replace(/ /g, '_')
    return (
      <Link className='SearchResult' to='page'
        params={{ title: escapedTitle }}>
        {title}
      </Link>
    )
  }
})
