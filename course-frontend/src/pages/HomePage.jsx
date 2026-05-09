function HomePage() {
  return (
    <section>
      <h1>Welcome to Course Manager</h1>

      <p>
        This app demonstrates React routing, navigation, login flow, protected
        views, and database-backed course data.
      </p>

      <div className="card">
        <h2>Day 12 Learning Goals</h2>
        <ul>
          <li>Create routes for different pages</li>
          <li>Navigate without reloading the browser</li>
          <li>Read dynamic values from the URL</li>
          <li>Login from the frontend</li>
          <li>Protect selected pages from anonymous users</li>
          <li>Read course data from the backend API</li>
        </ul>
      </div>
    </section>
  );
}

export default HomePage;