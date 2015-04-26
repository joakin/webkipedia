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
    var title = this.props.item.get('title')
    return (
      <Link className='SearchResult' to='page'
        params={{ title: title.replace(/ /g, '_') }}>
        {title}
      </Link>
    )
  }
})
