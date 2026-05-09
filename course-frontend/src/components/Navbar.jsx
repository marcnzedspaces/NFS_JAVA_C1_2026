import { Link } from "react-router-dom";

function Navbar(){
    return ( 
        <nav className="navbar">
            <div className="nav-brand">Course App</div>

            <div className="nav-links">
                <Link to="/">Home</Link>
                <Link to="/courses">Courses</Link>
                <Link to="/dashboard">Dashboard</Link>
                <Link to="/login">Login</Link>
            </div>
        </nav>
    );
}
export default Navbar;