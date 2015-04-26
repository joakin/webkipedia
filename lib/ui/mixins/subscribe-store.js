export default function (...stores) {
  return {
    getInitialState () { return this.getState() },
    componentDidMount () {
      stores.forEach((store) => store.onChange(this._onChange))
    },
    componentWillUnmount () {
      stores.forEach((store) => store.offChange(this._onChange))
    },
    _onChange () {
      if (this.isMounted()) { this.setState(this.getState()) }
    }
  }
}
