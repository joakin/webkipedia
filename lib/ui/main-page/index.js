import React from 'react'
import SearchBox from '../search-box'
import PageList from '../page-list'
import component from 'omniscient'
import router from '../../router'
import {debounce} from 'lodash'

import './main-page.less'

let {div, h1} = React.DOM

export default component('MainPage', function (cursor) {
  let query = cursor.getIn(['search', 'query'])
  let searchingClass = Boolean(query) ? 'is-searching' : ''
  let onQueryChange = debounce(
    (query) => router.redirectTo(`search/${query}`),
    200, { leading: true, maxTime: 300 }
  )
  return div({ className: 'MainPage ' + searchingClass },
    h1({ className: 'MainPage-header' }, 'Wikipedia'),
    SearchBox({ query: query, onChange: onQueryChange }),
    PageList({
      items: cursor.getIn(['search', 'results', 'list'])
    }))
})
