import React from 'react'
import {debounce} from 'lodash'
import {Actions, dispatch} from '../../actions'
import component from 'omniscient'

import './search-box.less'

let {div, form, input} = React.DOM

export default component('SearchBox', function ({query, onChange}) {
  let onQueryChange = () => onChange(this.refs.query.getDOMNode().value)
  return div({ className: 'SearchBox' },
      form({ className: 'SearchBox-form' },
        input({
          className: 'SearchBox-input',
          type: 'text',
          placeholder: 'Search',
          value: query,
          ref: 'query',
          onChange: onQueryChange
        })))
})
