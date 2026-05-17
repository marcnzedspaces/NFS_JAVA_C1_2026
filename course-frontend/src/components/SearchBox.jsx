function SearchBox({ searchTerm, onSearchChange, resultCount, totalCount }) {
  return (
    <div className="search-box">
      <input
        type="text"
        value={searchTerm}
        onChange={(event) => onSearchChange(event.target.value)}
        placeholder="Search by title, category, or status..."
      />

      {searchTerm && (
        <button type="button" onClick={() => onSearchChange("")}>
          Clear
        </button>
      )}

      <p>
        Showing {resultCount} of {totalCount} course(s)
      </p>
    </div>
  );
}

export default SearchBox;