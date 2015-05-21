import React from 'react'
import article from '../../api/article'
import component from 'omniscient'
import LeadImage from '../lead-image'
import PageHeader from '../page-header'

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
  let page = cursor.getIn(['page', 'content'], null)
  let loading = !page ? h6(null, 'Loading!') : null
  let lead = page && page.get('thumb') ? LeadImage(page.get('thumb')) : null
  return div({ className: 'Page' },
    lead,
    PageHeader({
      title: cursor.getIn(['page', 'title']),
      description: page && page.get('description')
    }),
    loading)
})
