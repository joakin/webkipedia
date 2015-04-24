import React from 'react'
import {Link} from 'react-router'

export default React.createClass({
  displayName: 'ButtonLink',
  propTypes: { className: React.PropTypes.string },
  render () {
    var className = this.props.className
    return (
      <Link {...this.props}
        className={'ButtonLink button ' + (className || '')}
        />
    )
  }
})
