import React from 'react'
import article from '../../api/article'
import component from 'omniscient'

import './page.less'

function loadArticle (state) {
  let title = state.getIn(['page', 'title'])
  // If the results are not loaded, then request them
  if (title && title !== state.getIn(['page', 'content', 'title'])) {
    state.setIn(['page', 'content'], null)
    article(title).then(result => {
      // If the result is relevant for the current page, swap it
      if (result.get('title') === state.getIn(['page', 'title'])) {
        state.setIn(['page', 'content'], result)
      }
    })
  }
}

var mixins = {
  componentWillMount () { loadArticle(this.props.__singleCursor) },
  componentWillReceiveProps (nextProps) { loadArticle(nextProps.__singleCursor) }
}

let {div, h1, h4, h6} = React.DOM

export default component('Page', mixins, function (cursor) {
  let title = (cursor.getIn(['page', 'title']) || '').replace(/_/g, ' ')
  let currentArticle = cursor.getIn(['page', 'content'], null)
  let loading = !currentArticle ? h6(null, 'Working on it!') : null
  let description = currentArticle ? currentArticle.get('description') : ''
  let thumb = currentArticle && currentArticle.get('thumb')
  let image = !thumb ? null : div({
    className: 'LeadImage',
    style: { backgroundImage: `url(${thumb.get('url')})` }
  })

  return div({ className: 'Page' },
    image,
    h1({}, title),
    h4({}, description),
    loading)
})
