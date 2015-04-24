import React from 'react'
import {Link} from 'react-router'

import './search-result.less'

export default React.createClass({
  displayName: 'SearchResult',
  propTypes: {
    title: React.PropTypes.string
  },
  render () {
    return (
      <Link className='SearchResult' to='page'
        params={{ title: this.props.title }}>
        {this.props.title}
      </Link>
    )
  }
})
