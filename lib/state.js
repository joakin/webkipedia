import immstruct from 'immstruct'

let state = immstruct({
  route: '',
  search: {
    query: null,
    results: {query: null, list: []}
  },
  page: {
    title: null,
    content: null
  }
})
export default state
