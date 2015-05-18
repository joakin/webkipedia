import React from 'react'
import article from '../../api/article'

import './page.less'

export default React.createClass({
  displayName: 'Page',
  propTypes: {
    state: React.PropTypes.any
  },
  componentWillMount () { this.maybeLoadArticle() },
  // componentWillReceiveProps () { this.maybeLoadArticle() },
  render () {
    let title = this.props.state.getIn(['router', 'params', 'title'], '').replace(/_/g, ' ')
    let currentArticle = this.props.state.get('currentArticle', null)
    let loading = !currentArticle ? (<h6>Working on it!</h6>) : null
    let description = currentArticle ? currentArticle.get('description') : ''
    let thumb = currentArticle && currentArticle.get('thumb')
    let image = !thumb ? null : (
        <div className='LeadImage'
          style={{backgroundImage: `url(${thumb.get('url')})`}}/>
    )
    return (
      <div className='Page'>
        {image}
        <h1>{title}</h1>
        <h4>{description}</h4>
        {loading}
      </div>
    )
  },
  maybeLoadArticle () {
    let title = this.props.state.getIn(['router', 'params', 'title'], '').replace(/_/g, ' ')
    this.props.state.remove('currentArticle')
    article(title).then(result => {
      if (result !== this.props.state.get('currentArticle')) {
        this.props.state.set('currentArticle', result)
      }
    })
  }
})
